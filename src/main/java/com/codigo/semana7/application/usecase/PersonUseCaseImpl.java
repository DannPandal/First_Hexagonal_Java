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
    public Person createPerson(Person persona) {
        return personRepositoryPort.save(persona);
    }

    @Override
    public Optional<Person> getPerson(Long id) {
        return personRepositoryPort.findById(id);
    }

    @Override
    public Person updatePerson(Person persona) {
        return personRepositoryPort.update(persona);
    }

    @Override
    public boolean deletePerson(Long id) {
        return personRepositoryPort.delete(id);
    }

}
