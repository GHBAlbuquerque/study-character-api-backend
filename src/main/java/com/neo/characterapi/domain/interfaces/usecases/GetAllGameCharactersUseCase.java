package com.neo.characterapi.domain.interfaces.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;

import java.util.List;

public interface GetAllGameCharactersUseCase {
    List<GameCharacter> execute();
}
