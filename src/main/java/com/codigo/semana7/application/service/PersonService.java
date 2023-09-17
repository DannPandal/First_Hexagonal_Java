package com.codigo.semana7.application.service;

import com.codigo.semana7.domain.model.Person;
import com.codigo.semana7.domain.ports.in.PersonUseCase;

import java.util.Optional;

public class PersonService implements PersonUseCase {

    // inyección de dependencia por constructor
    // inyección de puerto de entrada
    private final PersonUseCase personUseCase;

    public PersonService(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @Override
    public Person createPerson(Person person) {
        return personUseCase.createPerson(person);
    }

    @Override
    public Optional<Person> getPerson(Long id) {
        return personUseCase.getPerson(id);
    }

    @Override
    public Optional<Person> updatePerson(Person person) {
        return personUseCase.updatePerson(person);
    }

    @Override
    public boolean deletePerson(Long id) {
        return personUseCase.deletePerson(id);
    }

}
