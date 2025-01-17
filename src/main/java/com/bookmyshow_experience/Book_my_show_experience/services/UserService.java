package com.bookmyshow_experience.Book_my_show_experience.services;

import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.MailAPIUtil;

@Service
public class UserService {

    DatabaseAPIUtil databaseAPIUtil;
    MailAPIUtil mailAPIUtil;

    UserService(DatabaseAPIUtil databaseAPIUtil, MailAPIUtil mailAPIUtil) {
        this.databaseAPIUtil = databaseAPIUtil;
        this.mailAPIUtil = mailAPIUtil;

    }

    public void createUser(CreateUserRequestBody createUserRequestBody) {

        // call DB API
        try {
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
