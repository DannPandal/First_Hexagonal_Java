package com.codigo.semana7.application.service;

import com.codigo.semana7.domain.model.User;
import com.codigo.semana7.domain.ports.in.UserUseCase;

import java.util.Optional;

public class UserService implements UserUseCase {

    private final UserUseCase userUseCase;

    public UserService(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    public Optional<User> createUser(User user) {
        return userUseCase.createUser(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userUseCase.getUser(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        return userUseCase.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userUseCase.deleteUser(id);
    }
}
