package com.study.characterapi.domain.interfaces.repositories;

import com.study.characterapi.domain.entities.GameCharacter;

import java.util.Collection;
import java.util.Optional;

public interface GameCharacterRepository {

    Long save(GameCharacter character);

    Optional<GameCharacter> findbyId(Long id);

    Collection<GameCharacter> findAll();

    Boolean existsByName(String name);
}
