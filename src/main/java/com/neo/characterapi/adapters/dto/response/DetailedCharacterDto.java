package com.neo.characterapi.adapters.dto.response;

import com.neo.characterapi.domain.enums.CharacterStatus;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public record DetailedCharacterDto(
        Long id,
        String name,
        JobType jobType,
        JobAttributes jobAttributes,
        CharacterStatus characterStatus,
        Integer currentHealth
){}
