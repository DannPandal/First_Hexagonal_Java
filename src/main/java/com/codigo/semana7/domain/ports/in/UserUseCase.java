package com.codigo.semana7.domain.ports.in;

import com.codigo.semana7.domain.model.User;

import java.util.Optional;

public interface UserUseCase {
    Optional<User> createUser(User user);

    Optional<User> getUser(Long id);

    Optional<User> updateUser(User user);

    boolean deleteUser(Long id);

}
