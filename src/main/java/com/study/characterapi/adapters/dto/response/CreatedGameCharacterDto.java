package com.study.characterapi.adapters.dto.response;

import com.study.characterapi.domain.enums.JobType;

public record CreatedGameCharacterDto(
        Long id,
        String name,
        JobType jobType
) {
}

