package com.codigo.semana7.domain.ports.out;

import com.codigo.semana7.domain.model.Person;

import java.util.Optional;

public interface PersonRepositoryPort {

    Person save(Person persona);

    Optional<Person> findById(Long id);

    Person update(Person persona);

    // person delete repository port out
    boolean delete(Long id);
}
