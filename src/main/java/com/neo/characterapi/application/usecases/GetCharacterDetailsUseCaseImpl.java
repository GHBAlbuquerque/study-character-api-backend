package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.CharacterNotFoundException;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;

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
            throw new CharacterNotFoundException(String.format("Couldn't find GameCharacter with is %s ", id));
        }

        return optional.get();
    }
}
