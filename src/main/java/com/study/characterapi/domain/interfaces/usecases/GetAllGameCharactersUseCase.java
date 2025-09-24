package com.study.characterapi.domain.interfaces.usecases;

import com.study.characterapi.domain.entities.GameCharacter;

import java.util.List;

public interface GetAllGameCharactersUseCase {
    List<GameCharacter> execute();
}
