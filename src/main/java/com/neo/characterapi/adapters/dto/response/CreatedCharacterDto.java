package com.neo.characterapi.adapters.dto.response;

import com.neo.characterapi.domain.enums.JobType;

public record CreatedCharacterDto (
    Long id,
    String name,
    JobType jobType
){}

