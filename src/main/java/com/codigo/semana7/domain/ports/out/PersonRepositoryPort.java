package com.codigo.semana7.domain.ports.out;

import com.codigo.semana7.domain.model.Person;

import java.util.Optional;

public interface PersonRepositoryPort {

    Person save(Person person);

    Optional<Person> findById(Long id);

    Optional<Person> update(Person person);

    // person delete repository port out
    boolean deleteById(Long id);
}
