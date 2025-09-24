package com.study.characterapi.adapters.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BattleRequestDto(
        @NotNull
        @Min(0)
        Long firstCharacterId,

        @NotNull
        @Min(0)
        Long secondCharacterId
) {
}
