package com.neo.characterapi.domain.entities;

import com.neo.characterapi.application.strategy.JobStrategyFactory;
import com.neo.characterapi.domain.enums.JobType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class GameCharacterTest {

    @Test
    void isAlive_WhenCharacterIsAlive_ShouldReturnTrue() {
        final var character = createCharacter("Hero", JobType.WARRIOR);
        assertTrue(character.isAlive());
    }

    @Test
    void isDead_WhenCharacterIsDead_ShouldReturnTrue() {
        final var character = createCharacter("Mage", JobType.MAGE);
        character.killCharacter();
        assertTrue(character.isDead());
    }

    @Test
    void killCharacter_ShouldSetStatusToDeadAndHealthZero() {
        final var character = createCharacter("Hero", JobType.WARRIOR);
        character.killCharacter();
        assertEquals(0, character.getCurrentHealth());
    }

    @Test
    void reviveCharacter_ShouldRestoreHealthAndSetStatusAlive() {
        final var character = createCharacter("Mage", JobType.MAGE);
        character.killCharacter();
        character.reviveCharacter();
        assertEquals(character.getJobAttributes().getHealth(), character.getCurrentHealth());
    }

    @Test
    void takeDamage_WhenDamageLessThanHealth_ShouldReduceHealth() {
        final var character = createCharacter("Rogue", JobType.THIEF);
        character.takeDamage(4);

        final Integer expected = character.getJobAttributes().getHealth() - 4;
        assertEquals(expected, character.getCurrentHealth());
    }

    @Test
    void takeDamage_WhenDamageExceedsHealth_ShouldKillCharacter() {
        final var character = createCharacter("Paladin", JobType.WARRIOR);
        character.takeDamage(50);
        assertTrue(character.isDead());
    }

    @Test
    void getSpeed_ShouldReturnJobAttributeSpeed() {
        final var character = createCharacter("Hero", JobType.WARRIOR);
        final var expected = JobStrategyFactory.createJob(JobType.WARRIOR).calculateSpeed(character.getJobAttributes());
        assertEquals(expected, character.getSpeed());
    }

    @Test
    void getAttack_ShouldReturnJobAttributeAttack() {
        final var character = createCharacter("Mage", JobType.MAGE);
        final var expected = JobStrategyFactory.createJob(JobType.MAGE).calculateAttack(character.getJobAttributes());
        assertEquals(expected, character.getAttack());
    }

    private GameCharacter createCharacter(String name, JobType jobType) {
        return new GameCharacter(name, JobStrategyFactory.createJob(jobType));
    }
}