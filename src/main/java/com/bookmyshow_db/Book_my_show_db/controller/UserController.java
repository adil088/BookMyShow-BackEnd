package com.bookmyshow_db.Book_my_show_db.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_db.Book_my_show_db.models.AppUser;
import com.bookmyshow_db.Book_my_show_db.repository.AppUserRepository;

@RestController
@RequestMapping("/api/v1/db/user")
public class UserController {

    AppUserRepository appUserRepository;

    // concepter dependency injection
    @Autowired
    UserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody AppUser user) {
        appUserRepository.save(user);
        return new ResponseEntity("User got saved successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable UUID userId) {
        AppUser user = appUserRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

}
