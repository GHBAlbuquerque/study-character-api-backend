package com.neo.characterapi.domain.interfaces.usecases;

import com.neo.characterapi.domain.valueobjects.BattleResult;

public interface BattleUseCase {
    BattleResult execute(Long firstCharacterId, Long secondCharacterId);
}
