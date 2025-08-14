package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.CharacterNotFoundException;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCharacterDetailsUseCaseImplTest {

    @Mock
    private GameCharacterRepository characterRepository;

    @InjectMocks
    private GetCharacterDetailsUseCaseImpl useCase;

    @Test
    void execute_WhenCharacterExists_ShouldReturnCharacter() {
        final var character = createCharacter("Hero");
        when(characterRepository.findbyId(1L)).thenReturn(Optional.of(character));

        final var result = useCase.execute(1L);

        assertEquals(character, result);
    }

    @Test
    void execute_WhenCharacterDoesNotExist_ShouldThrowCharacterNotFoundException() {
        when(characterRepository.findbyId(999L)).thenReturn(Optional.empty());

        assertThrows(CharacterNotFoundException.class, () -> useCase.execute(999L));
    }

    private GameCharacter createCharacter(String name) {
        final var character = new GameCharacter(name, JobType.THIEF, JobType.THIEF.getBaseAttributes());
        return character;
    }
}