package com.neo.characterapi.adapters.dto.response;

import java.util.List;

public record BattleResultDto(
    Long winnerId,
    Long loserId,
    int winnerRemainingHp,
    List<String> battleLog
) {}


