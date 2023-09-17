package com.codigo.semana7.application.usecase;

import com.codigo.semana7.domain.model.User;
import com.codigo.semana7.domain.ports.in.UserUseCase;
import com.codigo.semana7.domain.ports.out.UserRepositoryPort;

import java.util.Optional;

public class UserUseCaseImpl implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        return userRepositoryPort.update(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepositoryPort.deleteById(id);
    }
}
