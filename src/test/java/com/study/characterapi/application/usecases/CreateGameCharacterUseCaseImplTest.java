package com.study.characterapi.application.usecases;

import com.study.characterapi.application.exceptions.CreateCharacterException;
import com.study.characterapi.application.strategy.JobStrategyFactory;
import com.study.characterapi.domain.entities.GameCharacter;
import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateGameCharacterUseCaseImplTest {

    @Mock
    private GameCharacterRepository characterRepository;

    @InjectMocks
    private CreateGameCharacterUseCaseImpl useCase;

    @Test
    void execute_WhenCharacterNameDoesNotExist_ShouldReturnCharacterWithId() {
        final var character = createCharacter("Hero");
        when(characterRepository.existsByName("Hero")).thenReturn(false);
        when(characterRepository.save(character)).thenReturn(1L);

        final var result = useCase.execute(character);

        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void execute_WhenCharacterNameExists_ShouldThrowCreateCharacterException() {
        final var character = createCharacter("New_Thief");
        when(characterRepository.existsByName("New_Thief")).thenReturn(true);

        assertThrows(CreateCharacterException.class, () -> useCase.execute(character));
    }

    private GameCharacter createCharacter(String name) {
        return new GameCharacter(name, JobStrategyFactory.createJob(JobType.THIEF));
    }
}