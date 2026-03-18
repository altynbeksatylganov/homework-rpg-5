package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public void setRandomSeed(long seed) {
        this.random = new Random(seed);
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        if (hero == null || boss == null || action == null) {
            result.setWinner("No winner");
            result.setRounds(0);
            result.addLine("Battle could not start because one or more inputs were null.");
            return result;
        }

        int rounds = 0;
        final int maxRounds = 10;

        result.addLine("Battle started: " + hero.getName() + " vs " + boss.getName());
        result.addLine("Hero action: " + action.getActionName());
        result.addLine("Effects: " + action.getEffectSummary());

        while (hero.isAlive() && boss.isAlive() && rounds < maxRounds) {
            rounds++;

            int heroBonus = random.nextInt(4);
            int heroDamage = action.getDamage() + heroBonus;
            boss.takeDamage(heroDamage);

            result.addLine("Round " + rounds + ": " + hero.getName()
                    + " deals " + heroDamage + " damage to " + boss.getName()
                    + ". Boss HP = " + boss.getHealth());

            if (!boss.isAlive()) {
                result.setWinner(hero.getName());
                result.setRounds(rounds);
                result.addLine(boss.getName() + " has been defeated.");
                return result;
            }

            int bossBonus = random.nextInt(3);
            int bossDamage = boss.getAttackPower() + bossBonus;
            hero.takeDamage(bossDamage);
            result.addLine("Round " + rounds + ": " + boss.getName()
                    + " deals " + bossDamage + " damage to " + hero.getName()
                    + ". Hero HP = " + hero.getHealth());

            if (!hero.isAlive()) {
                result.setWinner(boss.getName());
                result.setRounds(rounds);
                result.addLine(hero.getName() + " has fallen in battle.");
                return result;
            }
        }
        result.setRounds(rounds);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner(boss.getName());
        } else if (hero.getHealth() >= boss.getHealth()) {
            result.setWinner(hero.getName());
            result.addLine("Max rounds reached. Hero wins by remaining health.");
        } else {
            result.setWinner(boss.getName());
            result.addLine("Max rounds reached. Boss wins by remaining health.");
        }

        return result;
    }


}
