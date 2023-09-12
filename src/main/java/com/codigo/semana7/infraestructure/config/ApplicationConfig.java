package com.codigo.semana7.infraestructure.config;

import com.codigo.semana7.application.service.PersonService;
import com.codigo.semana7.application.usecase.PersonUseCaseImpl;
import com.codigo.semana7.domain.ports.out.PersonRepositoryPort;
import com.codigo.semana7.infraestructure.repository.PersonJpaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {


    //los beans
    @Bean
    public PersonService personaService(PersonRepositoryPort personRepositoryPort){
        return new PersonService(new PersonUseCaseImpl(personRepositoryPort));
    }

    public PersonRepositoryPort personRepositoryPort(PersonJpaRepositoryAdapter personJpaRepositoryAdapter){
        return personJpaRepositoryAdapter;
    }
}
