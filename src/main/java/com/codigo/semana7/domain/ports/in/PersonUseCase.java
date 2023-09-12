package com.codigo.semana7.domain.ports.in;

import com.codigo.semana7.domain.model.Person;

import java.util.Optional;

public interface PersonUseCase {
    Person createPerson(Person persona);

    Optional<Person> getPerson(Long id);

    Person updatePerson(Person persona);

    // case uses for delete por in
    boolean deletePerson(Long id);
}
