package com.study.characterapi.adapters.dto.response;

import com.study.characterapi.domain.enums.CharacterStatus;
import com.study.characterapi.domain.enums.JobType;

public record SimpleGameCharacterDto(
        Long id,
        String name,
        JobType jobType,
        CharacterStatus characterStatus
) {
}
