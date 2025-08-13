package com.neo.characterapi.domain.valueobjects;

import java.util.List;

public record BattleResult (
    Long winnerId,
    Long loserId,
    int winnerRemainingHp,
    List<String> battleLog
) {}


