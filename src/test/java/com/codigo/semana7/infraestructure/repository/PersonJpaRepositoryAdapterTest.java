package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.Person;
import com.codigo.semana7.infraestructure.entity.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonJpaRepositoryAdapterTest {

    @Mock  // va a suplantar al Persona Jpa Repository
    PersonJpaRepository personJpaRepository;  //inilizacion del mock

    @InjectMocks  // Inyecta los mocks en el objeto a testear (donde estan los metodos a testear)
    PersonJpaRepositoryAdapter personJpaRepositoryAdapter;

    @BeforeEach // Antes de cada test
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personJpaRepositoryAdapter = new PersonJpaRepositoryAdapter(personJpaRepository);
    }

    @Test
    void saveSuccessInPersonEntity() {

        // Lo que se envia al metodo de la clases a probar
        Person person = new Person(1L, "Dann", "Pandal", new Date(), "Masculino");  // se tiene un Modelo de person con datos

        // Lo que se va a enviar a BD como simulación
        PersonEntity personEntity = new PersonEntity(1L, "Dann", "Pandal", new Date(), "Masculino");

        // Simulando comportamiento
        when(personJpaRepository.save(Mockito.any(PersonEntity.class))).thenReturn(personEntity);

        // Ejecutando el metodo a testear
        Person personAdapter = personJpaRepositoryAdapter.save(person);

        // Verificando
        assertNotNull(personAdapter);
        assertEquals(person.getId(), personAdapter.getId());
        assertEquals(person.getName(), personAdapter.getName());
    }


    // Test para findById exitoso (en el que se espera que se encuentre el id)
    @Test
    void findByIdSuccessInPersonEntity() {
        // Lo que se envia al metodo de la clases a probar
        Long id = 5L;

        // Lo que se va a enviar a BD como simulación
        PersonEntity personEntity = new PersonEntity(5L, "Dann", "Pandal", new Date(), "Masculino");

        // Simulando comportanmiento
        when(personJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));

        // Ejecutando el metodo a testear
        Optional<Person> personAdapter = personJpaRepositoryAdapter.findById(id);

        // Verificando
        assertFalse(personAdapter.isEmpty());
        assertEquals(id, personAdapter.get().getId());
    }

    // Test para findById fallido (en el que se espera que no se encuentre el id)
    @Test
    void findByIdEmptyInPersonEntity() {
        // Lo que se envia al metodo de la clases a probar
        Long id = 5L;

        // Simulando comportanmiento
        when(personJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // Ejecutando el metodo a testear
        Optional<Person> personAdapter = personJpaRepositoryAdapter.findById(id);

        // Verificando
        // para confirmar que lista personAdapter es vacia
        assertTrue(personAdapter.isEmpty());

    }

    // Test para update exitoso (en el que se espera que se cambie los datos)
    @Test
    void updateSuccessInPersonEntity(){
        // Lo que se envia al metodo de la clases a probar
        Person person = new Person(1L, "Dann", "Pandal", new Date(), "Masculino");

        // Lo que se va a enviar a BD como simulación
        PersonEntity personEntity = new PersonEntity(1L, "Dann", "Pandal", new Date(), "Masculino");

        // Simulando comportanmiento
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        when(personJpaRepository.save(Mockito.any(PersonEntity.class))).thenReturn(personEntity);

        // Ejecutando el metodo a testear
        Optional<Person> personAdapter = personJpaRepositoryAdapter.update(person);

        // Verificando
        assertFalse(personAdapter.isEmpty());
        assertEquals(person.getId(), personAdapter.get().getId());
        assertEquals(person.getName(), personAdapter.get().getName());
        assertEquals(person.getLastName(), personAdapter.get().getLastName());
        assertEquals(person.getBirthday(), personAdapter.get().getBirthday());
        assertEquals(person.getGender(), personAdapter.get().getGender());
    }

    // Test Update fallido, retornando Empty
    @Test
    void updateReturnEmptyInPersonEntity(){
            // Lo que se envia al metodo de la clases a probar
            Person person = new Person();

            // Simulando comportanmiento
            when(personJpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

            // Ejecutando el metodo a testear
            Optional<Person> personAdapter = personJpaRepositoryAdapter.update(person);

            // Verificando
            assertTrue(personAdapter.isEmpty());
    }

    // Test exitoso al eliminar Person
    @Test
    void deleteSuccessInPersonEntity(){
        // Lo que se envia al metodo de la clases a probar
        Long id = 5L;

        // ************ Simulando comportanmiento ************
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        // simulando comportamiento de deleteById siendo un void
        doNothing().when(personJpaRepository).deleteById(Mockito.anyLong());

        // Ejecutando el metodo a testear
        boolean personAdapter = personJpaRepositoryAdapter.deleteById(id);

        // ***************** Verificando *****************
        assertTrue(personAdapter); // verificando la respuesta de un delete exitoso
        verify(personJpaRepository, times(1)).deleteById(id); // verificando que se llame al metodo deleteById
    }

    // Test para delete no encontrado
    @Test
    void deleteNotFoundInPersonEntity(){

        Long id = 5L;

        // Simulando comportanmiento
        when(personJpaRepository.existsById(Mockito.anyLong())).thenReturn(false);

        // Ejecutando el metodo a testear
        boolean personAdapter = personJpaRepositoryAdapter.deleteById(id);

        // Verificando
        assertFalse(personAdapter);
    }

}