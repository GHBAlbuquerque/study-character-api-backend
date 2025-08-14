package com.neo.characterapi.application.messages;

import com.neo.characterapi.domain.entities.GameCharacter;

public class BattleLogGenerator {

    public static final String BATTLE_INITIATED = "Battle between %s (%s) - %s HP and %s (%s) - %s HP begins!";
    public static final String SPEED_DRAW = "%s (%s speed) was faster than %s (%s speed) and will begin this round (%s).";
    public static final String ATTACK_CHAR = "%s attacks %s for %s, %s has %s HP remaining.";
    public static final String BATTLE_FINISHED = "%s wins the battle! %s still has %s HP remaining!";

    public static String generateBattleInitiatedLog(GameCharacter character1, GameCharacter character2) {
        return String.format(BattleLogGenerator.BATTLE_INITIATED,
                character1.getName(),
                character1.getJob(),
                character1.getCurrentHealth(),
                character2.getName(),
                character2.getJob(),
                character2.getCurrentHealth());
    }

    public static String generateSpeedDrawLog(GameCharacter faster, Integer speed1, GameCharacter slower, Integer speed2, Integer round) {
        return String.format(BattleLogGenerator.SPEED_DRAW,
                faster.getName(),
                speed1,
                slower.getName(),
                speed2,
                round);
    }

    public static String generateAttackLog(GameCharacter attacker, GameCharacter defender, Integer damage) {
        return String.format(BattleLogGenerator.ATTACK_CHAR,
                attacker.getName(),
                defender.getName(),
                damage,
                defender.getName(),
                defender.getCurrentHealth());
    }

    public static String generateBattleFinishedLog(GameCharacter winner) {
        return String.format(BattleLogGenerator.BATTLE_FINISHED,
                winner.getName(),
                winner.getName(),
                winner.getCurrentHealth());
    }
}
