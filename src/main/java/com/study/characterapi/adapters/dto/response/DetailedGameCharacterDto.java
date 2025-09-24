package com.study.characterapi.adapters.dto.response;

import com.study.characterapi.domain.enums.CharacterStatus;
import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.valueobjects.JobAttributes;

public record DetailedGameCharacterDto(
        Long id,
        String name,
        JobType jobType,
        JobAttributes jobAttributes,
        CharacterStatus characterStatus,
        Integer currentHealth,
        String attackModifierDescription,
        String speedModifierDescription
) {
}
