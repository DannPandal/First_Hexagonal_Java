package com.codigo.semana7.infraestructure.controller;

import com.codigo.semana7.application.service.UserService;
import com.codigo.semana7.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
//        User createUser = userService.createUser(user);
//        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        return userService.createUser(user)
                .map(user1 -> new ResponseEntity<>(user1, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){

        return userService.getUser(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return userService.updateUser(user)
                .map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
