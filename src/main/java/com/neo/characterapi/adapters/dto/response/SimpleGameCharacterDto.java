package com.neo.characterapi.adapters.dto.response;

import com.neo.characterapi.domain.enums.CharacterStatus;
import com.neo.characterapi.domain.enums.JobType;

public record SimpleGameCharacterDto(
        Long id,
        String name,
        JobType jobType,
        CharacterStatus characterStatus
){}
