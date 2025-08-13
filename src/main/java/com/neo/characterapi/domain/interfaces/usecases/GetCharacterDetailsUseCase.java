package com.neo.characterapi.domain.interfaces.usecases;

public interface GetCharacterDetailsUseCase {
    Character execute(Long id);
}
