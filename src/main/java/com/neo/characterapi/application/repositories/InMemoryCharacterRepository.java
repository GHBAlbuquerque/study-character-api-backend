package com.neo.characterapi.application.repositories;

import com.neo.characterapi.domain.interfaces.repositories.CharacterRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCharacterRepository implements CharacterRepository {

    private final Map<Long, Character> store = new ConcurrentHashMap<>();

    @Override
    public Long save(Character character) {
        //TODO
        return null;
    }

    @Override
    public Optional<Character> findbyId(Long id) {
        //TODO
        return Optional.empty();
    }

    @Override
    public Collection<Character> findAll() {
        //TODO
        return null;
    }
}
