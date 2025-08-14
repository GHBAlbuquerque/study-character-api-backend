package com.neo.characterapi.adapters.dto.response;

import java.util.List;

public record BattleResultDto(
        DetailedGameCharacterDto winner,
        DetailedGameCharacterDto loser,
        List<String> battleLog
) {
}


