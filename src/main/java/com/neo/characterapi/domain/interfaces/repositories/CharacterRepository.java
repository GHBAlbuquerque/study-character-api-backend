package com.neo.characterapi.domain.interfaces.repositories;

import com.neo.characterapi.domain.entities.GameCharacter;

import java.util.Collection;
import java.util.Optional;

public interface CharacterRepository {

    Long save(GameCharacter character);
    Optional<GameCharacter> findbyId(Long id);
    Collection<GameCharacter> findAll();
}
