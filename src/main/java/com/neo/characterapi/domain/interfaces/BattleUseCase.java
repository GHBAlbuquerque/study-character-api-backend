package com.neo.characterapi.domain.interfaces;

import com.neo.characterapi.domain.valueobjects.BattleResult;

public interface BattleUseCase {
    BattleResult execute(Long characterId1, Long characterId2);
}
