package com.study.characterapi.application.usecases;

import com.study.characterapi.application.exceptions.CharacterNotFoundException;
import com.study.characterapi.domain.entities.GameCharacter;
import com.study.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.study.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;

import java.util.Optional;

public class GetCharacterDetailsUseCaseImpl implements GetGameCharacterDetailsUseCase {

    private final GameCharacterRepository characterRepository;

    public GetCharacterDetailsUseCaseImpl(GameCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public GameCharacter execute(Long id) {
        final Optional<GameCharacter> optional = characterRepository.findbyId(id);

        if (optional.isEmpty()) {
            throw new CharacterNotFoundException(String.format("Couldn't find GameCharacter with id %s ", id));
        }

        return optional.get();
    }
}
