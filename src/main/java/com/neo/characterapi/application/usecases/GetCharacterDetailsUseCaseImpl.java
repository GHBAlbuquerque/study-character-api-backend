package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.CharacterNotFoundException;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.CharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.GetCharacterDetailsUseCase;

import java.util.Optional;

public class GetCharacterDetailsUseCaseImpl implements GetCharacterDetailsUseCase {

    private final CharacterRepository characterRepository;

    public GetCharacterDetailsUseCaseImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public GameCharacter execute(Long id) {
        final Optional<GameCharacter> optional = characterRepository.findbyId(id);

        if (optional.isEmpty()) {
            throw new CharacterNotFoundException(String.format("Couldn't find GameCharacter with is %s ", id));
        }

        return optional.get();
    }
}
