package com.neo.characterapi.adapters.dto.response;

import com.neo.characterapi.domain.enums.CharacterStatus;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public record SimpleCharacterDto(
        Long id,
        String name,
        JobType jobType,
        CharacterStatus characterStatus
){}
