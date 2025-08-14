package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.CreateCharacterException;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.CreateGameCharacterUseCase;

public class CreateGameCharacterUseCaseImpl implements CreateGameCharacterUseCase {

    private final GameCharacterRepository characterRepository;

    public CreateGameCharacterUseCaseImpl(GameCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public GameCharacter execute(GameCharacter character) {
        if (characterRepository.existsByName(character.getName())) {
            throw new CreateCharacterException(String.format("Character with name %s already exists", character.getName()));
        }

        final Long id = characterRepository.save(character);
        character.setId(id);
        return character;
    }
}
