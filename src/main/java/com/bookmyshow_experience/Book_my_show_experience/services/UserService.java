package com.bookmyshow_experience.Book_my_show_experience.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;

@Service
public class UserService {

    DatabaseAPIUtil databaseAPIUtil;

    @Autowired
    UserService(DatabaseAPIUtil databaseAPIUtil) {
        this.databaseAPIUtil = databaseAPIUtil;
    }

    public void createUser(CreateUserRequestBody createUserRequestBody) {

        // call DB API
        databaseAPIUtil.createUser(createUserRequestBody);

    }

}
