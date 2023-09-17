package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.Person;
import com.codigo.semana7.domain.ports.out.PersonRepositoryPort;
import com.codigo.semana7.infraestructure.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonJpaRepositoryAdapter implements PersonRepositoryPort {

    /*
    Person Jpa Adaptaer donde se implementan los metodos de PersonRepositoryPort
    Logica para respuesta.
    */
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
    public Optional<Person> update(Person person) {
        PersonEntity personEntity = PersonEntity.fromDomainModel(person);

        if(personJpaRepository.existsById(person.getId())){
            PersonEntity updatedPersonEntity = personJpaRepository.save(personEntity);
            return Optional.of(updatedPersonEntity.toDomainModel());
        }
        return Optional.empty();
    }


    @Override
    public boolean deleteById(Long id) {
        if(personJpaRepository.existsById(id)){
            personJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
