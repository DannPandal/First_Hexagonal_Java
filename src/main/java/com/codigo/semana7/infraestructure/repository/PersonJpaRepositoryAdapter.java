package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.Person;
import com.codigo.semana7.domain.ports.out.PersonRepositoryPort;
import com.codigo.semana7.infraestructure.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonJpaRepositoryAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository personJpaRepository;

    public PersonJpaRepositoryAdapter(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Person save(Person persona) {
        PersonEntity personEntity = PersonEntity.fromDomainModel(persona);
        PersonEntity savePersonEntity = personJpaRepository.save(personEntity);
        return savePersonEntity.toDomainModel();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personJpaRepository.findById(id).map(PersonEntity::toDomainModel);
    }

    @Override
    public Person update(Person persona) {
        return personJpaRepository.save(PersonEntity.fromDomainModel(persona)).toDomainModel();
    }

    @Override
    public boolean delete(Long id) {
        return personJpaRepository.findById(id)
                .map(personEntity -> {
                        personJpaRepository.delete(personEntity);
                        return true;
                }).orElse(false);
    }
}
