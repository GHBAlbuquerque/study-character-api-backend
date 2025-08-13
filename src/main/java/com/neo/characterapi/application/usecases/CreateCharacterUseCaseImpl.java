package com.neo.characterapi.application.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.CharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.CreateCharacterUseCase;

public class CreateCharacterUseCaseImpl implements CreateCharacterUseCase {

    private final CharacterRepository characterRepository;

    public CreateCharacterUseCaseImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public GameCharacter execute(GameCharacter character) {
        final Long id = characterRepository.save(character);
        character.setId(id);
        return character;
    }
}
