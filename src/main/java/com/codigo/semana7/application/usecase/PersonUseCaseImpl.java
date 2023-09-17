package com.codigo.semana7.application.usecase;

import com.codigo.semana7.domain.model.Person;
import com.codigo.semana7.domain.ports.in.PersonUseCase;
import com.codigo.semana7.domain.ports.out.PersonRepositoryPort;

import java.util.Optional;


public class PersonUseCaseImpl implements PersonUseCase {

    // inyección de dependencia por constructor
    // inyección de puerto de salida
    private final PersonRepositoryPort personRepositoryPort;

    public PersonUseCaseImpl(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public Person createPerson(Person person) {
        return personRepositoryPort.save(person);
    }

    @Override
    public Optional<Person> getPerson(Long id) {
        return personRepositoryPort.findById(id);
    }

    @Override
    public Optional<Person> updatePerson(Person person) {
        return personRepositoryPort.update(person);
    }

    @Override
    public boolean deletePerson(Long id) {
        return personRepositoryPort.deleteById(id);
    }

}
