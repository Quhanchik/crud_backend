package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        String response = userService.createUser(user);

        if(response == "bad") {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        String response = userService.createUser(user);

        if(response == "good")
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return HttpStatus.OK;
    }
}
