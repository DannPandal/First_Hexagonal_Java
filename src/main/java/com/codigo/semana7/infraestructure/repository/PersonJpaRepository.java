package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.infraestructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {

}
