package com.codigo.semana7.domain.ports.in;

import com.codigo.semana7.domain.model.Person;

import java.util.Optional;

public interface PersonUseCase {
    Person createPerson(Person person);

    Optional<Person> getPerson(Long id);

    Optional<Person> updatePerson(Person person);

    // case uses for delete por id
    boolean deletePerson(Long id);

}
