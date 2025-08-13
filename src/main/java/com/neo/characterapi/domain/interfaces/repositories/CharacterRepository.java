package com.neo.characterapi.domain.interfaces.repositories;

import java.util.Collection;
import java.util.Optional;

public interface CharacterRepository {

    Long save(Character character);
    Optional<Character> findbyId(Long id);
    Collection<Character> findAll();
}
