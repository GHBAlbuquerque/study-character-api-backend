package com.neo.characterapi.adapters.dto.request;

import com.neo.characterapi.adapters.dto.validators.annotations.ValidCharacter;
import com.neo.characterapi.domain.enums.JobType;

@ValidCharacter
public record CreateCharacterDto (
        String name,
        String job
){}
