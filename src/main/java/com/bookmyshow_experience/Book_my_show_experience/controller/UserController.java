package com.bookmyshow_experience.Book_my_show_experience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.services.UserService;
import com.bookmyshow_experience.Book_my_show_experience.utility.DatabaseInsertionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/exp/user")
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequestBody createUserRequestBody) {

        try {
            userService.createUser(createUserRequestBody);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (DatabaseInsertionException databaseInsertionException) {
            return new ResponseEntity<>(databaseInsertionException.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
