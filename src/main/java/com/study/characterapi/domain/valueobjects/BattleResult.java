package com.study.characterapi.domain.valueobjects;

import com.study.characterapi.domain.entities.GameCharacter;

import java.util.List;

public record BattleResult(
        GameCharacter winner,
        GameCharacter loser,
        List<String> battleLog
) {
}


