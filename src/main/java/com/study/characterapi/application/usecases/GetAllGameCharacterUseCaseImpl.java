package com.study.characterapi.application.usecases;

import com.study.characterapi.domain.entities.GameCharacter;
import com.study.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.study.characterapi.domain.interfaces.usecases.GetAllGameCharactersUseCase;

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
