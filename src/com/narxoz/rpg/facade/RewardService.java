package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }
        String winner = battleResult.getWinner();
        if (winner == null) {
            return "No reward";
        }
        if (winner.toLowerCase().contains("hero")
                || winner.toLowerCase().contains("knight")
                || winner.toLowerCase().contains("hunter")
                || winner.toLowerCase().contains("arthas")) {
            if (battleResult.getRounds() <= 3) {
                return "Legendary chest: 500 gold, epic weapon, boss trophy";
            } else if (battleResult.getRounds() <= 6) {
                return "Victory reward: 250 gold, rare armor, healing potion";
            } else {
                return "Survivor reward: 100 gold and a recovery potion";
            }
        }
        return "No reward - the boss kept the treasure.";
    }
}
