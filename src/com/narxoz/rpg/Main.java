package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Arthas Hero", 100);
        BossEnemy boss = new BossEnemy("Infernal Dragon", 120, 15);


        AttackAction basic = new BasicAttack("Sword Slash", 12);
        AttackAction fireAttack = new FireRuneDecorator(basic);
        AttackAction poisonAttack = new PoisonCoatingDecorator(basic);
        AttackAction fullCombo = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)
                )
        );
        System.out.println("--- Decorator Preview ---");
        printAction("Base attack", basic);
        printAction("Fire attack", fireAttack);
        printAction("Poison attack", poisonAttack);
        printAction("Full combo", fullCombo);

        System.out.println("\n--- Facade Preview ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, fullCombo);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("\nBattle log:");
        for (String line : result.getLog()) {
            System.out.println("- " + line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printAction(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  Name   : " + action.getActionName());
        System.out.println("  Damage : " + action.getDamage());
        System.out.println("  Effects: " + action.getEffectSummary());
        System.out.println();

    }
}
