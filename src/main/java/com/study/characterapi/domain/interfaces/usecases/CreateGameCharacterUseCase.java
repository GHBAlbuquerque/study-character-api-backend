package com.study.characterapi.domain.interfaces.usecases;

import com.study.characterapi.domain.entities.GameCharacter;

public interface CreateGameCharacterUseCase {
    GameCharacter execute(GameCharacter character);
}
