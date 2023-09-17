package com.codigo.semana7.infraestructure.config;

import com.codigo.semana7.application.service.PersonService;
import com.codigo.semana7.application.service.UserService;
import com.codigo.semana7.application.usecase.PersonUseCaseImpl;
import com.codigo.semana7.application.usecase.UserUseCaseImpl;
import com.codigo.semana7.domain.ports.out.PersonRepositoryPort;
import com.codigo.semana7.domain.ports.out.UserRepositoryPort;
import com.codigo.semana7.infraestructure.repository.PersonJpaRepositoryAdapter;
import com.codigo.semana7.infraestructure.repository.UserJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PersonService personaService(PersonRepositoryPort personRepositoryPort){
        return new PersonService(new PersonUseCaseImpl(personRepositoryPort));
    }

    @Bean
    public PersonRepositoryPort personRepositoryPort(PersonJpaRepositoryAdapter personJpaRepositoryAdapter){
        return personJpaRepositoryAdapter;
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(new UserUseCaseImpl(userRepositoryPort));
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserJpaRepositoryAdapter userJpaRepositoryAdapter){
        return userJpaRepositoryAdapter;
    }
}
