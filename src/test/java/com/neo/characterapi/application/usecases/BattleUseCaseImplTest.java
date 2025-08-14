package com.neo.characterapi.application.usecases;

import com.neo.characterapi.application.exceptions.InvalidBattleException;
import com.neo.characterapi.application.strategy.JobStrategyFactory;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BattleUseCaseImplTest {

    @Mock
    private GameCharacterRepository characterRepository;

    @Mock
    private GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase;

    @InjectMocks
    private BattleUseCaseImpl battleUseCase;

    @Test
    void execute_WhenDifferentAliveCharacters_ShouldReturnBattleResult() {
        final var character1 = createCharacter("New_Mage", JobType.MAGE);
        final var character2 = createCharacter("New_Thief", JobType.THIEF);

        when(getGameCharacterDetailsUseCase.execute(1L)).thenReturn(character1);
        when(getGameCharacterDetailsUseCase.execute(2L)).thenReturn(character2);

        final var result = battleUseCase.execute(1L, 2L);

        assertNotNull(result);
        assertTrue(!result.battleLog().isEmpty());
    }

    @Test
    void execute_WhenSameCharacterIds_ShouldThrowInvalidBattleException() {
        assertThrows(InvalidBattleException.class, () -> battleUseCase.execute(1L, 1L));
    }

    @Test
    void execute_WhenOneCharacterIsDead_ShouldThrowInvalidBattleException() {
        final var character1 = createCharacter("New_Mage", JobType.MAGE);
        final var character2 = createCharacter("New_Thief", JobType.THIEF);
        character2.killCharacter();

        when(getGameCharacterDetailsUseCase.execute(1L)).thenReturn(character1);
        when(getGameCharacterDetailsUseCase.execute(2L)).thenReturn(character2);

        assertThrows(InvalidBattleException.class, () -> battleUseCase.execute(1L, 2L));
    }

    private GameCharacter createCharacter(String name, JobType jobType) {
        return new GameCharacter(name, JobStrategyFactory.createJob(jobType));
    }

}