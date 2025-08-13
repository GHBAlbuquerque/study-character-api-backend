package com.neo.characterapi.adapters.dto.request;

import com.neo.characterapi.adapters.dto.validators.annotations.ValidGameCharacter;

@ValidGameCharacter
public record CreateGameCharacterDto(
        String name,
        String job
){}
