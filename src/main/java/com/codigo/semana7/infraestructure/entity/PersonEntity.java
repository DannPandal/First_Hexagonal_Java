package com.codigo.semana7.infraestructure.entity;

import com.codigo.semana7.domain.model.Person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity  // Es una entidad de base de datos
@Getter
@Setter
@Table(name = "persons") // name in database
public class PersonEntity { //Es la que va mapear con base de datos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private Date birthday;
    private String gender;

    public PersonEntity() {
    }

    public PersonEntity(Long id, String name, String lastName, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }


    // Transforma el modelo persona a Person Entity
    public static PersonEntity fromDomainModel(Person person) {
        return new PersonEntity(
                person.getId(),
                person.getName(),
                person.getLastName(),
                person.getBirthday(),
                person.getGender()
        );
    }

    public Person toDomainModel() {
        return new Person( this.id, this.name, this.lastName, this.birthday, this.gender);
    }
}
