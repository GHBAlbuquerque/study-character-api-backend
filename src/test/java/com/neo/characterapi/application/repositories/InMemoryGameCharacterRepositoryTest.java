package com.neo.characterapi.application.repositories;

import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InMemoryGameCharacterRepositoryTest {

    private InMemoryGameCharacterRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryGameCharacterRepository();
    }

    @Test
    void save_ShouldReturnGeneratedId() {
        final var character = createCharacter("Hero");
        final var id = repository.save(character);
        assertEquals(1L, id);
    }

    @Test
    void findById_WhenCharacterExists_ShouldReturnCharacter() {
        final var character = createCharacter("Famous_Mage");
        final var id = repository.save(character);
        final var found = repository.findbyId(id);
        assertTrue(found.isPresent());
    }

    @Test
    void findById_WhenCharacterDoesNotExist_ShouldReturnEmpty() {
        final var found = repository.findbyId(999L);
        assertTrue(found.isEmpty());
    }

    @Test
    void findAll_WhenCharactersExist_ShouldReturnAllCharacters() {
        final var character1 = createCharacter("Rogue");
        final var character2 = createCharacter("Cleric");
        repository.save(character1);
        repository.save(character2);
        final var all = repository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void findAll_WhenNoCharactersExist_ShouldReturnEmptyCollection() {
        final var all = repository.findAll();
        assertTrue(all.isEmpty());
    }

    @Test
    void existsByName_WhenNameExists_ShouldReturnTrue() {
        final var character = createCharacter("Paladin");
        repository.save(character);
        final var exists = repository.existsByName("Paladin");
        assertTrue(exists);
    }

    @Test
    void existsByName_WhenNameDoesNotExist_ShouldReturnFalse() {
        final var exists = repository.existsByName("Nonexistent");
        assertFalse(exists);
    }

    private GameCharacter createCharacter(String name) {
        final var character = new GameCharacter(name, JobType.MAGE, JobType.MAGE.getBaseAttributes());
        return character;
    }
}