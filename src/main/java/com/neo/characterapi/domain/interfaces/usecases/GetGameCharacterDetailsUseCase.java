package com.neo.characterapi.domain.interfaces.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;

public interface GetGameCharacterDetailsUseCase {
    GameCharacter execute(Long id);
}
