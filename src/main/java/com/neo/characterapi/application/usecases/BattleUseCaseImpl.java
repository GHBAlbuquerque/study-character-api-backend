package com.neo.characterapi.application.usecases;

import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.neo.characterapi.domain.valueobjects.BattleResult;

public class BattleUseCaseImpl implements BattleUseCase {

    private final GameCharacterRepository characterRepository;

    public BattleUseCaseImpl(GameCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public BattleResult execute(Long characterId1, Long characterId2) {
        //TODO
        return null;
    }
}
