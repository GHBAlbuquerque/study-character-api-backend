package com.study.characterapi.application.mappers;

import com.study.characterapi.adapters.dto.response.BattleResultDto;
import com.study.characterapi.domain.valueobjects.BattleResult;

public class BattleMapper {

    public static BattleResultDto toBattleResultDto(BattleResult battleResult) {
        return new BattleResultDto(
                GameCharacterMapper.toDetailedGameCharacterDto(battleResult.winner()),
                GameCharacterMapper.toDetailedGameCharacterDto(battleResult.loser()),
                battleResult.battleLog()
        );
    }
}
