package com.neo.characterapi.domain.valueobjects;

import com.neo.characterapi.domain.entities.GameCharacter;

import java.util.List;

public record BattleResult(
        GameCharacter winner,
        GameCharacter loser,
        List<String> battleLog
) {
}


