package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.Person;
import com.codigo.semana7.domain.model.User;
import com.codigo.semana7.infraestructure.entity.PersonEntity;
import com.codigo.semana7.infraestructure.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserJpaRepositoryAdapterTest {

    @Mock
    UserJpaRepository userJpaRepository; // inilizacion del mock
    @Mock
    PersonJpaRepository personJpaRepository; // inilizacion del mock

    @InjectMocks    // Inyecta los mocks en el objeto a testear (donde estan los metodos a testear)
    UserJpaRepositoryAdapter userJpaRepositoryAdapter;

    @BeforeEach // Antes de cada test
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userJpaRepositoryAdapter = new UserJpaRepositoryAdapter(userJpaRepository, personJpaRepository);
    }

    // Test exitoso al guardar usuario
    @Test
    void saveSuccessInUserEntity() {
        // Lo que se envia al metodo de la clases a probar
        Person person1 = new Person();
        person1.setId(1L);
        User user = new User(1L, "DannPandal", "root", "dev.pandal@gmail.com", person1);

        // Lo que se va a enviar a BD como simulación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(1L);
        UserEntity userEntity = new UserEntity(1L, "DannPandal", "root", "dev.pandal@gmail.com", personEntity);

        // Simulando comportamiento
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(userEntity.getPerson()));
        when(userJpaRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        // Ejecutando el metodo a testear
        Optional<User> savedUserAdapter = userJpaRepositoryAdapter.save(user);

        // Verificando
        assertNotNull(savedUserAdapter);
        assertEquals(user.getId(), savedUserAdapter.get().getId());
        assertEquals(user.getUsername(), savedUserAdapter.get().getUsername());
        assertEquals(user.getPassword(), savedUserAdapter.get().getPassword());
        assertEquals(user.getEmail(), savedUserAdapter.get().getEmail());
        assertEquals(user.getPerson().getId(), savedUserAdapter.get().getPerson().getId());
    }

    // Test que retorna vacio, al intentar guardar usuario cuando no se encuentra el id de la persona
    @Test
    void saveReturnEmptyInUserEntityWhenNotFoundPersonEntity(){
        // Lo que se envia al metodo de la clases a probar
        Person person1 = new Person();
        person1.setId(1L);
        User user = new User();
        user.setPerson(person1);

        // Simulando comportamiento
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);

        // Ejecutando el metodo a testear
        Optional<User> notSavedUserAdapter = userJpaRepositoryAdapter.save(user);

        // Verificando
        assertTrue(notSavedUserAdapter.isEmpty());
    }


    // Test exitoso al buscar usuario por id
    @Test
    void findByIdSuccessInUserEntity() {
        // Lo que se envia al metodo de la clases a probar
        Long id = 1L;

        // Lo que se va a enviar a BD como simulación
        UserEntity userEntity = new UserEntity(1L, "DannPandal", "root", "dev.pandal@gmail.com", new PersonEntity());

        // Simulando comportanmiento
        when(userJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(userEntity));

        // Ejecutando el metodo a testear
        Optional<User> userAdapter = userJpaRepositoryAdapter.findById(id);

        // Verificando
        assertFalse(userAdapter.isEmpty());
        assertEquals(id, userAdapter.get().getId());
    }

    // Test exitoso al actualizar usuario
    @Test
    void updateSuccessInUserEntity() {
        // Lo que se envia al metodo de la clases a probar
        Person person1 = new Person();
        person1.setId(1L);
        User user = new User(1L, "DannPandal", "root", "dev.pandal@gmail.com", person1);

        // Lo que se va a enviar a BD como simulación
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(1L);
        UserEntity userEntity = new UserEntity(1L, "DannPandal", "root", "dev.pandal@gmail.com", personEntity);

        // Simulando comportamiento
        when(userJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
//        when(userJpaRepository.existsById(Mockito.anyLong()) && personJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(userEntity.getPerson()));
        when(userJpaRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        // Ejecutando el metodo a testear
        Optional<User> updatedUserAdapter = userJpaRepositoryAdapter.update(user);

        // Verificando
        assertFalse(updatedUserAdapter.isEmpty());
        assertEquals(user.getId(), updatedUserAdapter.get().getId());
        assertEquals(user.getUsername(), updatedUserAdapter.get().getUsername());
        assertEquals(user.getPassword(), updatedUserAdapter.get().getPassword());
        assertEquals(user.getEmail(), updatedUserAdapter.get().getEmail());
        assertEquals(user.getPerson().getId(), updatedUserAdapter.get().getPerson().getId());

    }

    // Test que retorna vacio, al intentar actualizar usuario cuando no se encuentra el id del usuario
    @Test
    void updateReturnEmptyInUserEntityWhenNotFoundUserEntity() {
        // Lo que se envia al metodo de la clases a probar
        User user = new User(1L, "DannPandal", "root", "dev.pandal@gmail.com", new Person());

        // Simulando comportamiento
        when(userJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);

        // Ejecutando el metodo a testear
        Optional<User> updatedUserAdapter = userJpaRepositoryAdapter.update(user);

        // Verificando
        assertTrue(updatedUserAdapter.isEmpty());

    }
    // Test que retorna vacio, al intentar actualizar usuario cuando no se encuentra el id de la persona
    @Test
    void updateReturnEmptyInUserEntityWhenNotFoundPersonEntity() {
        // Lo que se envia al metodo de la clases a probar
        User user = new User(1L, "DannPandal", "root", "dev.pandal@gmail.com", new Person());

        // Simulando comportamiento
        when(userJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);

        // Ejecutando el metodo a testear
        Optional<User> updatedUserAdapter = userJpaRepositoryAdapter.update(user);

        // Verificando
        assertTrue(updatedUserAdapter.isEmpty());

    }

    // Test exitoso al eliminar usuario
    @Test
    void deleteByIdSuccessInUserEntity() {
        // Lo que se envia al metodo de la clases a probar
        Long id = 1L;

        // Simulando comportanmiento
        when(userJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        doNothing().when(userJpaRepository).deleteById(Mockito.anyLong());

        // Ejecutando el metodo a testear
        boolean deletedUserAdapter = userJpaRepositoryAdapter.deleteById(id);

        // Verificando
        assertTrue(deletedUserAdapter);
        verify(userJpaRepository, times(1)).deleteById(Mockito.anyLong()); // verificando que se llame al metodo deleteById
    }

    // Test que retorna false, al intentar eliminar usuario cuando no se encuentra el id
    @Test
    void deleteByIdReturnFalseInUserEntityWhenNotFoundId() {
        // Lo que se envia al metodo de la clases a probar
        Long id = 1L;

        // Simulando comportanmiento
        when(userJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);

        // Ejecutando el metodo a testear
        boolean deletedUserAdapter = userJpaRepositoryAdapter.deleteById(id);

        // Verificando
        assertFalse(deletedUserAdapter);
    }
}