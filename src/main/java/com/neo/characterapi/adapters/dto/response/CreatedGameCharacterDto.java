package com.neo.characterapi.adapters.dto.response;

import com.neo.characterapi.domain.enums.JobType;

public record CreatedGameCharacterDto(
    Long id,
    String name,
    JobType jobType
){}

