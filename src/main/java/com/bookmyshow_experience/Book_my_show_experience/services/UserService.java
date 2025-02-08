package com.bookmyshow_experience.Book_my_show_experience.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.Errors.InvalidUserException;
import com.bookmyshow_experience.Book_my_show_experience.Errors.WrongCredentialsException;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.LoginRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.MailAPIUtil;

@Service
public class UserService {

    DatabaseAPIUtil databaseAPIUtil;
    MailAPIUtil mailAPIUtil;
    PasswordEncoder passwordEncoder;

    UserService(DatabaseAPIUtil databaseAPIUtil, MailAPIUtil mailAPIUtil, PasswordEncoder passwordEncoder) {
        this.databaseAPIUtil = databaseAPIUtil;
        this.mailAPIUtil = mailAPIUtil;
        this.passwordEncoder = passwordEncoder;

    }

    public void createUser(CreateUserRequestBody createUserRequestBody) {

        // call DB API
        try {
            createUserRequestBody.setPassword(passwordEncoder.encode(createUserRequestBody.getPassword()));
            databaseAPIUtil.createUser(createUserRequestBody);
        } catch (Exception e) {
            throw e;
        }

        try {
            mailAPIUtil.sendUserRegistrationEmail(createUserRequestBody.getEmail(), createUserRequestBody.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public ResponseEntity<String> loginUser(LoginRequestBody loginRequestBody) {
        try {

            AppUser foundLoginUser = databaseAPIUtil.getUserByEmail(loginRequestBody.getEmail());

            if (foundLoginUser == null) {
                throw new InvalidUserException("User not found!");
            }

            boolean passwordMatches = passwordEncoder.matches(loginRequestBody.getPassword(),
                    foundLoginUser.getPassword());

            System.out.println("Password matches?: " + passwordMatches);

            if (!passwordMatches) {
                throw new InvalidUserException("Password is incorrect!");
            }

            return new ResponseEntity<>("Login success!",
                    HttpStatus.OK);
        } catch (InvalidUserException e) {
            throw new InvalidUserException("Bad credentials!");
        }

    }

}
