package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.InvalidBattleException;
import com.neo.characterapi.application.messages.BattleLogGenerator;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
import com.neo.characterapi.domain.valueobjects.BattleResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleUseCaseImpl implements BattleUseCase {

    private final GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase;

    public BattleUseCaseImpl(GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase) {
        this.getGameCharacterDetailsUseCase = getGameCharacterDetailsUseCase;
    }

    @Override
    public BattleResult execute(Long firstCharacterId, Long secondCharacterId) {
        if (firstCharacterId.equals(secondCharacterId)) {
            throw new InvalidBattleException("Characters cannot be the same. Please chose a different opponent.");
        }

        final GameCharacter character1 = getGameCharacterDetailsUseCase.execute(firstCharacterId);
        final GameCharacter character2 = getGameCharacterDetailsUseCase.execute(secondCharacterId);

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

    private List<GameCharacter> drawSpeed(GameCharacter character1, GameCharacter character2,List<String> battleLog, Random random, Integer round) {
        List<GameCharacter> attackOrder = new ArrayList<>();

        do {
            int speed1 = (int) Math.ceil(random.nextDouble() * character1.getSpeed());
            int speed2 = (int) Math.ceil(random.nextDouble() * character2.getSpeed());

            if (speed1 > speed2) {
                attackOrder.add(character1);
                attackOrder.add(character2);
                battleLog.add(BattleLogGenerator.generateSpeedDrawLog(character1, speed1, character2, speed2, round));
                break;
            } else if (speed2 > speed1) {
                attackOrder.add(character2);
                attackOrder.add(character1);
                battleLog.add(BattleLogGenerator.generateSpeedDrawLog(character2, speed2, character1, speed1, round));
                break;
            }

        } while (true);

        return attackOrder;
    }

    private void initiateTurn(GameCharacter attacker, GameCharacter attacked, List<String> battleLog, Random random) {
        double damage = random.nextDouble() * attacker.getAttack();
        int damageRounded = (int) Math.ceil(damage);
        attacked.takeDamage(damageRounded);

        battleLog.add(BattleLogGenerator.generateAttackLog(attacker, attacked, damageRounded));
    }

}
