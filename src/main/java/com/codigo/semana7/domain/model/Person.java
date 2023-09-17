package com.codigo.semana7.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String lastName;
    private Date birthday;
    private String gender;

    public Person(Long id, String name, String lastName, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

}
