package com.neo.characterapi.domain.interfaces.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;

public interface CreateGameCharacterUseCase {
    GameCharacter execute(GameCharacter character);
}
