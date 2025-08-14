package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.InvalidBattleException;
import com.neo.characterapi.application.messages.BattleLogGenerator;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
import com.neo.characterapi.domain.valueobjects.BattleResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BattleUseCaseImpl implements BattleUseCase {

    private final GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase;

    public BattleUseCaseImpl(GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase) {
        this.getGameCharacterDetailsUseCase = getGameCharacterDetailsUseCase;
    }

    @Override
    public BattleResult execute(Long firstCharacterId, Long secondCharacterId) {
        final GameCharacter character1 = getGameCharacterDetailsUseCase.execute(firstCharacterId);
        final GameCharacter character2 = getGameCharacterDetailsUseCase.execute(secondCharacterId);

        if (Objects.equals(firstCharacterId, secondCharacterId)) {
            throw new InvalidBattleException("Characters cannot be the same. Please chose a different opponent.");
        }

        if (isAnyCharacterDead(character1, character2)) {
            throw new InvalidBattleException("One of the characters is dead, battle cannot continue.");
        }

        final List<String> battleLog = new ArrayList<>();
        final Random random = new Random();

        int round = 1;
        GameCharacter winner = null;
        GameCharacter loser = null;

        battleLog.add(BattleLogGenerator.generateBattleInitiatedLog(character1, character2));

        while (character1.isAlive() && character2.isAlive()) {

            List<GameCharacter> attackOrder = drawSpeed(character1, character2, battleLog, random, round);
            GameCharacter first = attackOrder.get(0);
            GameCharacter second = attackOrder.get(1);

            initiateTurn(first, second, battleLog, random);
            if (!second.isAlive()) {
                battleLog.add(BattleLogGenerator.generateBattleFinishedLog(first));
                winner = first;
                loser = second;
                break;
            }

            initiateTurn(second, first, battleLog, random);
            if (!first.isAlive()) {
                battleLog.add(BattleLogGenerator.generateBattleFinishedLog(second));
                winner = second;
                loser = first;
                break;
            }

            round++;
        }

        return new BattleResult(winner, loser, battleLog);
    }

    private boolean isAnyCharacterDead(GameCharacter character1, GameCharacter character2) {
        return character1.isDead() || character2.isDead();
    }

    private List<GameCharacter> drawSpeed(GameCharacter character1, GameCharacter character2, List<String> battleLog, Random random, Integer round) {
        if (character1.getSpeed() == 0 && character2.getSpeed() == 0) {
            if (random.nextBoolean()) {
                return List.of(character1, character2);
            } else {
                return List.of(character2, character1);
            }
        }

        while (true) {
            int rolledSpeed1 = (int) Math.ceil(random.nextDouble() * character1.getSpeed());
            int rolledSpeed2 = (int) Math.ceil(random.nextDouble() * character2.getSpeed());

            if (rolledSpeed1 != rolledSpeed2) {
                GameCharacter first = rolledSpeed1 > rolledSpeed2 ? character1 : character2;
                GameCharacter second = (first.equals(character1)) ? character2 : character1;

                int firstSpeed = Math.max(rolledSpeed1, rolledSpeed2);
                int secondSpeed = Math.min(rolledSpeed1, rolledSpeed2);

                battleLog.add(BattleLogGenerator.generateSpeedDrawLog(first, firstSpeed, second, secondSpeed, round));

                return List.of(first, second);
            }
        }
    }

    private void initiateTurn(GameCharacter attacker, GameCharacter attacked, List<String> battleLog, Random random) {
        double damage = random.nextDouble() * attacker.getAttack();
        int damageRounded = (int) Math.round(damage);
        attacked.takeDamage(damageRounded);

        battleLog.add(BattleLogGenerator.generateAttackLog(attacker, attacked, damageRounded));
    }

}
