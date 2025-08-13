package com.neo.characterapi.adapters.controllers;

import com.neo.characterapi.adapters.dto.request.CreateCharacterDto;
import com.neo.characterapi.adapters.dto.response.CreatedCharacterDto;
import com.neo.characterapi.adapters.dto.response.SimpleCharacterDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @PostMapping
    public ResponseEntity<CreatedCharacterDto> createCharacter(@RequestBody @Valid CreateCharacterDto request) {
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
