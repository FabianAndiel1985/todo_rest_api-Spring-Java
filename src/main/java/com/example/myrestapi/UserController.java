package com.example.myrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User newUser) {

        //generate secret for user
        newUser.setSecret(UUID.randomUUID().toString());
        User savedUser = userRepository.save(newUser);
        //es ginge genauso HTTP Status Ok es ist wichtig hier eine Variation zu haben
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    private ResponseEntity<User> getUser(@RequestParam(value = "id") int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity("no user found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user.get(), HttpStatus.CREATED);
    }

    @GetMapping("/validate")
    private ResponseEntity<String> validate(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        Optional<User> validUser = userRepository.findByEmailAndPassword(email,password);

        if(!validUser.isPresent()) {
            return new ResponseEntity("There is no valid user",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>("API Secret : " + validUser.get().getSecret(),HttpStatus.OK);
    }

}
