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
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;

    public PersonEntity() {
    }

    public PersonEntity(Long id, String nombre, String apellido, Date fechaNacimiento, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }


    // Transforma el modelo persona a Person Entity
    public static PersonEntity fromDomainModel(Person person) {
        return new PersonEntity(
                person.getId(),
                person.getNombre(),
                person.getApellido(),
                person.getFechaNacimiento(),
                person.getGenero()
        );
    }

    public Person toDomainModel() {
        return new Person( this.id, this.nombre, this.apellido, this.fechaNacimiento, this.genero);
    }
}
