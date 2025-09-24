package com.study.characterapi.application.mappers;

import com.study.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.study.characterapi.adapters.dto.response.CreatedGameCharacterDto;
import com.study.characterapi.adapters.dto.response.DetailedGameCharacterDto;
import com.study.characterapi.adapters.dto.response.SimpleGameCharacterDto;
import com.study.characterapi.application.strategy.JobStrategyFactory;
import com.study.characterapi.domain.entities.GameCharacter;
import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.valueobjects.Job;

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
