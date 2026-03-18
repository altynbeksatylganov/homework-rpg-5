package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Preparation failed: hero, boss, or action is missing.";
        }
        if (!hero.isAlive()) {
            return "Preparation failed: hero is already defeated.";
        }
        if (!boss.isAlive()) {
            return "Preparation failed: boss is already defeated.";
        }


        return "Preparation complete: " +
                hero.getName() + " enters the dungeon to fight " +
                boss.getName() + " using [" + action.getActionName() + "] " +
                "with damage " + action.getDamage() + ".";
    }
}
