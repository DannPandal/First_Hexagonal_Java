package com.codigo.semana7.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private  Long id;
    private String username;
    private String password;
    private String email;
    private Person person;

    public User(Long id, String username, String password, String email, Person person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.person = person;
    }
}
