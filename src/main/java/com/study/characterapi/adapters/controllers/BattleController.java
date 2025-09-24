package com.study.characterapi.adapters.controllers;

import com.study.characterapi.adapters.dto.request.BattleRequestDto;
import com.study.characterapi.adapters.dto.response.BattleResultDto;
import com.study.characterapi.application.mappers.BattleMapper;
import com.study.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.study.characterapi.domain.valueobjects.BattleResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/battles")
public class BattleController {

    private final BattleUseCase battleUseCase;

    public BattleController(BattleUseCase battleUseCase) {
        this.battleUseCase = battleUseCase;
    }

    @PostMapping
    public ResponseEntity<BattleResultDto> battle(@RequestBody @Valid BattleRequestDto battleRequest) {
        final BattleResult battleResult = battleUseCase.execute(battleRequest.firstCharacterId(), battleRequest.secondCharacterId());

        return ResponseEntity.ok(BattleMapper.toBattleResultDto(battleResult));
    }
}
