package com.codigo.semana7.domain.ports.out;

import com.codigo.semana7.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> save(User user);

    Optional<User> findById(Long id);

    Optional<User> update(User user);

    boolean deleteById(Long id);
}
