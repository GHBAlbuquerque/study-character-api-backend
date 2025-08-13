package com.neo.characterapi.adapters.dto.request;

import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public record CreateCharacterDto (
        String name,
        JobType jobType,
        JobAttributes jobAttributes
){}
