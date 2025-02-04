package com.bookmyshow_db.Book_my_show_db.controller;

import java.util.UUID;

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
import com.bookmyshow_db.Book_my_show_db.repository.TheaterRepository;

@RestController
@RequestMapping("/api/v1/db/user")
public class UserController {

    AppUserRepository appUserRepository;
    TheaterRepository theaterRepository;

    // concepter dependency injection
    UserController(AppUserRepository appUserRepository, TheaterRepository theaterRepository) {
        this.appUserRepository = appUserRepository;
        this.theaterRepository = theaterRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        System.out.println("Saving user....");
        appUserRepository.save(user);
        System.out.println("User saved successfully");
        return new ResponseEntity<String>("User got saved successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable UUID userId) {
        AppUser user = appUserRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity<AppUser> getUserByEmail(@PathVariable String emailId) {
        AppUser user = appUserRepository.findByEmail(emailId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

}
