package com.neo.characterapi.adapters.controllers;

import com.neo.characterapi.adapters.dto.request.CreateCharacterDto;
import com.neo.characterapi.adapters.dto.response.CreatedCharacterDto;
import com.neo.characterapi.adapters.dto.response.SimpleCharacterDto;
import com.neo.characterapi.domain.interfaces.usecases.CreateCharacterUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetAllCharactersUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetCharacterDetailsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CreateCharacterUseCase createCharacterUseCase;
    private final GetAllCharactersUseCase getAllCharactersUseCase;
    private final GetCharacterDetailsUseCase getCharacterDetailsUseCase;

    public CharacterController(CreateCharacterUseCase createCharacterUseCase, GetAllCharactersUseCase getAllCharactersUseCase, GetCharacterDetailsUseCase getCharacterDetailsUseCase) {
        this.createCharacterUseCase = createCharacterUseCase;
        this.getAllCharactersUseCase = getAllCharactersUseCase;
        this.getCharacterDetailsUseCase = getCharacterDetailsUseCase;
    }

    @PostMapping()
    public ResponseEntity<CreatedCharacterDto> createCharacter(@RequestBody @Valid CreateCharacterDto request) {
        System.out.println("CREATE CHAR");
        return null; /* TODO */
    }

    @GetMapping
    public ResponseEntity<SimpleCharacterDto> getAllCharacters() {
        return null; /* TODO */
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleCharacterDto> getCharacterDetails(@PathVariable Long id) {
        return null; /* TODO */
    }

}
