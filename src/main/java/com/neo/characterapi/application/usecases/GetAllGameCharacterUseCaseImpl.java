package com.neo.characterapi.application.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.GetAllGameCharactersUseCase;

import java.util.List;

public class GetAllGameCharacterUseCaseImpl implements GetAllGameCharactersUseCase {

    private final GameCharacterRepository characterRepository;

    public GetAllGameCharacterUseCaseImpl(GameCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<GameCharacter> execute() {
        return characterRepository.findAll().stream().toList();
    }
}
