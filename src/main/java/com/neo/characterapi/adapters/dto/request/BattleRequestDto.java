package com.neo.characterapi.adapters.dto.request;

public record BattleRequestDto(
        Long firstCharacterId,
        Long secondCharacterId
) {
}
