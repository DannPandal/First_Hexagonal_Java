package com.codigo.semana7.infraestructure.controller;

import com.codigo.semana7.application.service.PersonService;
import com.codigo.semana7.domain.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person createPerson = personService.createPerson(person);
        System.out.println("creando entity: ");
        return new ResponseEntity<>(createPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        System.out.println("id: para ger person id" );
        return personService.getPerson(id)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                //new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        Person updatePerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatePerson, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
//        String deletePerson = personService.deletePerson(id);
//        return new ResponseEntity<>(deletePerson, HttpStatus.OK);
        return personService.getPerson(id)
                .map(person -> {
//                    boolean deletedPerson = personService.deletePerson(id);
                    if (personService.deletePerson(id)) {
                        return new ResponseEntity<>("Persona eliminada", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Persona no eliminada", HttpStatus.NOT_FOUND);
                    }
//                    return new ResponseEntity<>("Persona eliminada", HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Persona no encontrada",HttpStatus.NOT_FOUND));
    }
}
