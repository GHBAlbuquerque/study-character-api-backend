package com.neo.characterapi.application.repositories;

import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryGameCharacterRepository implements GameCharacterRepository {

    private final AtomicLong lastUsedId= new AtomicLong(0);
    private final Map<Long, GameCharacter> store = new ConcurrentHashMap<>();

    @Override
    public Long save(GameCharacter character) {
        final var id = lastUsedId.incrementAndGet();
        character.setId(id);
        store.put(id, character);

        return id;
    }

    @Override
    public Optional<GameCharacter> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Collection<GameCharacter> findAll() {
        return store.values();
    }

    @Override
    public Boolean existsByName(String name) {
        return store.values().stream().anyMatch(gameCharacter -> gameCharacter.getName().equals(name));
    }
}
