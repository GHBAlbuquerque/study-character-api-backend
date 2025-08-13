package com.neo.characterapi.infrastructure.config;

import com.neo.characterapi.application.repositories.InMemoryGameCharacterRepository;
import com.neo.characterapi.application.usecases.BattleUseCaseImpl;
import com.neo.characterapi.application.usecases.CreateGameCharacterUseCaseImpl;
import com.neo.characterapi.application.usecases.GetAllGameCharacterUseCaseImpl;
import com.neo.characterapi.application.usecases.GetCharacterDetailsUseCaseImpl;
import com.neo.characterapi.domain.interfaces.repositories.GameCharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.neo.characterapi.domain.interfaces.usecases.CreateGameCharacterUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetAllGameCharactersUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetGameCharacterDetailsUseCase;
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
    public BattleUseCase battleUseCase(GameCharacterRepository gameCharacterRepository) {
        return new BattleUseCaseImpl(gameCharacterRepository);
    }

    @Bean
    public GameCharacterRepository gameCharacterRepository() {
        return new InMemoryGameCharacterRepository();
    }
}
