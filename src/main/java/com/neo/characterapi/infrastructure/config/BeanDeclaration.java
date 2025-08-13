package com.neo.characterapi.infrastructure.config;

import com.neo.characterapi.application.repositories.InMemoryCharacterRepository;
import com.neo.characterapi.application.usecases.BattleUseCaseImpl;
import com.neo.characterapi.application.usecases.CreateCharacterUseCaseImpl;
import com.neo.characterapi.application.usecases.GetAllCharacterUseCaseImpl;
import com.neo.characterapi.application.usecases.GetCharacterDetailsUseCaseImpl;
import com.neo.characterapi.domain.interfaces.repositories.CharacterRepository;
import com.neo.characterapi.domain.interfaces.usecases.BattleUseCase;
import com.neo.characterapi.domain.interfaces.usecases.CreateCharacterUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetAllCharactersUseCase;
import com.neo.characterapi.domain.interfaces.usecases.GetCharacterDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDeclaration {

    @Bean
    public CreateCharacterUseCase createNotificationUseCase(CharacterRepository characterRepository) {
        return new CreateCharacterUseCaseImpl(characterRepository);
    }

    @Bean
    public GetAllCharactersUseCase getAllCharactersUseCase(CharacterRepository characterRepository) {
        return new GetAllCharacterUseCaseImpl(characterRepository);
    }

    @Bean
    public GetCharacterDetailsUseCase getCharacterDetailsUseCase(CharacterRepository characterRepository) {
        return new GetCharacterDetailsUseCaseImpl(characterRepository);
    }

    @Bean
    public BattleUseCase battleUseCase(CharacterRepository characterRepository) {
        return new BattleUseCaseImpl(characterRepository);
    }

    @Bean
    public CharacterRepository characterRepository() {
        return new InMemoryCharacterRepository();
    }
}
