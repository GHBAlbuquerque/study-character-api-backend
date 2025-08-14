package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.messages.BattleLogGenerator;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.neo.characterapi.domain.valueobjects.BattleResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleUseCaseImpl implements BattleUseCase {

    private final GameCharacterRepository characterRepository;
    private final GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase;

    public BattleUseCaseImpl(GameCharacterRepository characterRepository, GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase) {
        this.characterRepository = characterRepository;
        this.getGameCharacterDetailsUseCase = getGameCharacterDetailsUseCase;
    }

    @Override
    public BattleResult execute(Long firstCharacterId, Long secondCharacterId) {
        final GameCharacter character1 = getGameCharacterDetailsUseCase.execute(firstCharacterId);
        final GameCharacter character2 = getGameCharacterDetailsUseCase.execute(secondCharacterId);
        final List<String> battleLog = new ArrayList<>();
        final Random random = new Random();

        while(character1.isAlive() && character2.isAlive()) {
            battleLog.add(BattleLogGenerator.generateBattleInitiatedLog(character1, character2));

            GameCharacter first, second;

            double speed1 = random.nextDouble() * character1.getSpeed();
            double speed2 = random.nextDouble() * character2.getSpeed();

            do {
                if (speed1 > speed2) {
                    first = character1;
                    second = character2;
                    battleLog.add(BattleLogGenerator.generateSpeedDrawLog(first, speed1, second, speed2));
                } else {
                    first = character2;
                    second = character1;
                    battleLog.add(BattleLogGenerator.generateSpeedDrawLog(first, speed2, second, speed1));
                }
                break;
            } while (true);



        }

        //UPDATE CHARS IN THE REPOSITORY
        //RETURN BATTLE RESULT
        return null;
    }

    private void attack(GameCharacter attacker, GameCharacter attacked, List<String> battleLog, Random random) {
        double damage = random.nextDouble() * attacker.getAttack();
        attacked.takeDamage(damage);

        battleLog.add(BattleLogGenerator.generateAttackLog(attacker, attacked, damage));
    }

}
