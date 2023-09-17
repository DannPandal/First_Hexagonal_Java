package com.codigo.semana7.infraestructure.entity;

import com.codigo.semana7.domain.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
//    @Column(name = "person_id")
    private PersonEntity person;


    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, String email, PersonEntity person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.person = person;
    }

    public static UserEntity fromDomainModel(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                PersonEntity.fromDomainModel(user.getPerson())
        );
    }

    public User toDomainModel() {
        return new User(
                this.id,
                this.username,
                this.password,
                this.email,
                this.person.toDomainModel()
        );
    }

}
