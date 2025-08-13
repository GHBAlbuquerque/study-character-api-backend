package com.neo.characterapi.application.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.CharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.GetAllCharactersUseCase;

import java.util.List;

public class GetAllCharacterUseCaseImpl implements GetAllCharactersUseCase {

    private final CharacterRepository characterRepository;

    public GetAllCharacterUseCaseImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<GameCharacter> execute() {
        return characterRepository.findAll().stream().toList();
    }
}
