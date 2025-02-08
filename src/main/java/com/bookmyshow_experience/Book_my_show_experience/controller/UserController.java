package com.bookmyshow_experience.Book_my_show_experience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_experience.Book_my_show_experience.Errors.DatabaseInsertionException;
import com.bookmyshow_experience.Book_my_show_experience.Errors.InvalidUserException;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.LoginRequestBody;
// import com.bookmyshow_experience.Book_my_show_experience.security.JwtUtil;
import com.bookmyshow_experience.Book_my_show_experience.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/exp/user")
public class UserController {

    UserService userService;
    // JwtUtil jwtUtil;

    UserController(UserService userService
    // , JwtUtil jwtUtil
    ) {
        this.userService = userService;
        // this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequestBody createUserRequestBody) {

        try {
            userService.createUser(createUserRequestBody);
            // String credentials = createUserRequestBody.getEmail() + ":" +
            // createUserRequestBody.getPassword();
            // String token = jwtUtil.generateToken(credentials);
            return new ResponseEntity<>(
                    // token,
                    HttpStatus.CREATED);
        } catch (DatabaseInsertionException databaseInsertionException) {
            return new ResponseEntity<>(databaseInsertionException.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestBody loginRequestBody) {

        try {
            userService.loginUser(loginRequestBody);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidUserException invalidUserException) {
            return new ResponseEntity<>(invalidUserException.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
