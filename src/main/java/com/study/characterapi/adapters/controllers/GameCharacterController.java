package com.study.characterapi.adapters.controllers;

import com.study.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.study.characterapi.adapters.dto.response.CreatedGameCharacterDto;
import com.study.characterapi.adapters.dto.response.DetailedGameCharacterDto;
import com.study.characterapi.adapters.dto.response.SimpleGameCharacterDto;
import com.study.characterapi.application.mappers.GameCharacterMapper;
import com.study.characterapi.domain.entities.GameCharacter;
import com.study.characterapi.domain.interfaces.usecases.CreateGameCharacterUseCase;
import com.study.characterapi.domain.interfaces.usecases.GetAllGameCharactersUseCase;
import com.study.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/characters")
public class GameCharacterController {

    private final CreateGameCharacterUseCase createGameCharacterUseCase;
    private final GetAllGameCharactersUseCase getAllGameCharactersUseCase;
    private final GetGameCharacterDetailsUseCase getCharacterDetailsUseCase;

    public GameCharacterController(CreateGameCharacterUseCase createGameCharacterUseCase, GetAllGameCharactersUseCase getAllGameCharactersUseCase, GetGameCharacterDetailsUseCase getCharacterDetailsUseCase) {
        this.createGameCharacterUseCase = createGameCharacterUseCase;
        this.getAllGameCharactersUseCase = getAllGameCharactersUseCase;
        this.getCharacterDetailsUseCase = getCharacterDetailsUseCase;
    }

    @PostMapping
    public ResponseEntity<CreatedGameCharacterDto> createCharacter(@RequestBody @Valid CreateGameCharacterDto request) throws URISyntaxException {
        final GameCharacter character = GameCharacterMapper.toGameCharacter(request);
        final GameCharacter result = createGameCharacterUseCase.execute(character);

        final URI uri = new URI("/characters/" + result.getId());
        return ResponseEntity.created(uri).body(GameCharacterMapper.toCreatedGameCharacterDto(character));
    }

    @GetMapping
    public ResponseEntity<List<SimpleGameCharacterDto>> getAllCharacters() {
        final List<GameCharacter> result = getAllGameCharactersUseCase.execute();

        return ResponseEntity.ok(result.stream().map(GameCharacterMapper::toSimpleGameCharacterDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedGameCharacterDto> getCharacterDetails(@PathVariable Long id) {
        final GameCharacter result = getCharacterDetailsUseCase.execute(id);

        return ResponseEntity.ok(GameCharacterMapper.toDetailedGameCharacterDto(result));
    }

}
