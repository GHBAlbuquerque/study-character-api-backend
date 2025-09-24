package com.study.characterapi.infrastructure.config;

import com.study.characterapi.application.repositories.InMemoryGameCharacterRepository;
import com.study.characterapi.application.usecases.BattleUseCaseImpl;
import com.study.characterapi.application.usecases.CreateGameCharacterUseCaseImpl;
import com.study.characterapi.application.usecases.GetAllGameCharacterUseCaseImpl;
import com.study.characterapi.application.usecases.GetCharacterDetailsUseCaseImpl;
import com.study.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.study.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.study.characterapi.domain.interfaces.usecases.CreateGameCharacterUseCase;
import com.study.characterapi.domain.interfaces.usecases.GetAllGameCharactersUseCase;
import com.study.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDeclaration {

    @Bean
    public CreateGameCharacterUseCase createGameCharacterUseCase(GameCharacterRepository gameCharacterRepository) {
        return new CreateGameCharacterUseCaseImpl(gameCharacterRepository);
    }

    @Bean
    public GetAllGameCharactersUseCase getAllGameCharactersUseCase(GameCharacterRepository gameCharacterRepository) {
        return new GetAllGameCharacterUseCaseImpl(gameCharacterRepository);
    }

    @Bean
    public GetGameCharacterDetailsUseCase getGameCharacterDetailsUseCase(GameCharacterRepository gameCharacterRepository) {
        return new GetCharacterDetailsUseCaseImpl(gameCharacterRepository);
    }

    @Bean
    public BattleUseCase battleUseCase(GetGameCharacterDetailsUseCase getCharacterDetailsUseCase) {
        return new BattleUseCaseImpl(getCharacterDetailsUseCase);
    }

    @Bean
    public GameCharacterRepository gameCharacterRepository() {
        return new InMemoryGameCharacterRepository();
    }
}
