package com.codigo.semana7.infraestructure.controller;

import com.codigo.semana7.application.service.PersonService;
import com.codigo.semana7.domain.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/person")
public class PersonController {

    /* Controller of Person from get up information */
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
        return personService.getPerson(id)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                //new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
//        Person updatePerson = personService.updatePerson(person);
//        return new ResponseEntity<>(., HttpStatus.OK);

        //Optional<Person> optionalPerson = Optional.ofNullable(personService.updatePerson(person));
//        if (optionalPerson.isPresent()){
//            return new ResponseEntity<>(optionalPerson.get(), HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return personService.updatePerson(person)
                .map(person1 -> new ResponseEntity<>(person1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){

        if (personService.deletePerson(id)) {
            return new ResponseEntity<>("Person deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND);
        }

    }

}
