package com.study.characterapi.domain.interfaces.usecases;

import com.study.characterapi.domain.valueobjects.BattleResult;

public interface BattleUseCase {
    BattleResult execute(Long firstCharacterId, Long secondCharacterId);
}
