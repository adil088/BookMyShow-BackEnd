package com.bookmyshow_experience.Book_my_show_experience.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
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

}
