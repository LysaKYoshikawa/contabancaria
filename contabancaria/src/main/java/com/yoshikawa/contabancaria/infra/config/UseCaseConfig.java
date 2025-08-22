package com.yoshikawa.contabancaria.infra.config;

import com.yoshikawa.contabancaria.domain.useCase.UserCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UserCreateUseCase userCreateUseCase() {
        return new UserCreateUseCase();
    }
}
