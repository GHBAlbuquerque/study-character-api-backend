package com.neo.characterapi.application.mappers;

import com.neo.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.neo.characterapi.adapters.dto.response.CreatedGameCharacterDto;
import com.neo.characterapi.adapters.dto.response.DetailedGameCharacterDto;
import com.neo.characterapi.adapters.dto.response.SimpleGameCharacterDto;
import com.neo.characterapi.application.strategy.JobStrategyFactory;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.Job;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public class GameCharacterMapper {

    public static GameCharacter toGameCharacter(CreateGameCharacterDto createDto) {
        final JobType jobType = JobType.valueOf(createDto.job().toUpperCase());
        final Job job = JobStrategyFactory.createJob(jobType);

        return new GameCharacter(createDto.name(), job);
    }

    public static CreatedGameCharacterDto toCreatedGameCharacterDto(GameCharacter character) {
        return new CreatedGameCharacterDto(
                character.getId(),
                character.getName(),
                character.getJobType()
        );
    }

    public static DetailedGameCharacterDto toDetailedGameCharacterDto(GameCharacter character) {
        final JobType jobType = character.getJobType();

        return new DetailedGameCharacterDto(
                character.getId(),
                character.getName(),
                jobType,
                character.getJobAttributes(),
                character.getCharacterStatus(),
                character.getCurrentHealth(),
                jobType.getAttackModifierDescription(),
                jobType.getSpeedModifierDescription()
        );
    }

    public static SimpleGameCharacterDto toSimpleGameCharacterDto(GameCharacter character) {
        return new SimpleGameCharacterDto(
                character.getId(),
                character.getName(),
                character.getJobType(),
                character.getCharacterStatus()
        );
    }
}
