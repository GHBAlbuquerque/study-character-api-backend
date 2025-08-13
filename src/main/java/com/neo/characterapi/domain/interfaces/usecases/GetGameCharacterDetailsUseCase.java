package com.neo.characterapi.domain.interfaces.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;

public interface GetCharacterDetailsUseCase {
    GameCharacter execute(Long id);
}
