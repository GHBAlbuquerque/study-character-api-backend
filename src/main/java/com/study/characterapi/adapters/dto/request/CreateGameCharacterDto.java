package com.study.characterapi.adapters.dto.request;

import com.study.characterapi.adapters.dto.validators.annotations.ValidGameCharacter;

@ValidGameCharacter
public record CreateGameCharacterDto(
        String name,
        String job
) {
}
