package com.neo.characterapi.application.usecases;

import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllGameCharacterUseCaseImplTest {

    @Mock
    private GameCharacterRepository characterRepository;

    @InjectMocks
    private GetAllGameCharacterUseCaseImpl useCase;

    @Test
    void execute_WhenCharactersExist_ShouldReturnAllCharacters() {
        final var character1 = createCharacter("Hero");
        final var character2 = createCharacter("Mage");
        when(characterRepository.findAll()).thenReturn(List.of(character1, character2));

        final var result = useCase.execute();

        assertEquals(2, result.size());
    }

    @Test
    void execute_WhenNoCharactersExist_ShouldReturnEmptyList() {
        when(characterRepository.findAll()).thenReturn(List.of());

        final var result = useCase.execute();

        assertTrue(result.isEmpty());
    }

    private GameCharacter createCharacter(String name) {
        return new GameCharacter(name, JobType.WARRIOR, JobType.WARRIOR.getBaseAttributes());
    }
}