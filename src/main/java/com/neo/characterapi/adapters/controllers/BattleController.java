package com.neo.characterapi.adapters.controllers;

import com.neo.characterapi.adapters.dto.request.BattleRequestDto;
import com.neo.characterapi.adapters.dto.response.BattleResultDto;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
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
    public ResponseEntity<BattleResultDto> battle(@RequestBody BattleRequestDto battleRequest) {
        return null; /* TODO */
    }
}
