package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.User;
import com.codigo.semana7.domain.ports.out.UserRepositoryPort;
import com.codigo.semana7.infraestructure.entity.PersonEntity;
import com.codigo.semana7.infraestructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJpaRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final PersonJpaRepository personJpaRepository;

    public UserJpaRepositoryAdapter(UserJpaRepository userJpaRepository, PersonJpaRepository personJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Optional<User> save(User user) {

        UserEntity userEntity = UserEntity.fromDomainModel(user);
        userEntity.setId(0L);

        if (personJpaRepository.existsById(user.getPerson().getId())) {
            Optional<PersonEntity> p1 = personJpaRepository.findById(user.getPerson().getId());
            userEntity.setPerson(p1.get());
        }
        else{
            return Optional.empty();
        }

        UserEntity saveUserEntity = userJpaRepository.save(userEntity);
        return Optional.of(saveUserEntity.toDomainModel());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> update(User user) {
        UserEntity userEntity = UserEntity.fromDomainModel(user);

        if (! userJpaRepository.existsById(user.getId()) ){
            return Optional.empty();
        }
        if (! personJpaRepository.existsById(user.getPerson().getId())) {
            return Optional.empty();
        }
        Optional<PersonEntity> p1 = personJpaRepository.findById(user.getPerson().getId());

        userEntity.setPerson(p1.get());

        UserEntity updatedUserEntity = userJpaRepository.save(userEntity);

        return Optional.of(updatedUserEntity.toDomainModel());
    }

    @Override
    public boolean deleteById(Long id) {
        if(userJpaRepository.existsById(id)){
            userJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
