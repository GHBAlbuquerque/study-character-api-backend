package com.neo.characterapi.application.mappers;

import com.neo.characterapi.adapters.dto.response.BattleResultDto;
import com.neo.characterapi.domain.valueobjects.BattleResult;

public class BattleMapper {

    public static BattleResultDto toBattleResultDto(BattleResult battleResult) {
        return new BattleResultDto(
                GameCharacterMapper.toDetailedGameCharacterDto(battleResult.winner()),
                GameCharacterMapper.toDetailedGameCharacterDto(battleResult.loser()),
                battleResult.battleLog()
        );
    }
}
