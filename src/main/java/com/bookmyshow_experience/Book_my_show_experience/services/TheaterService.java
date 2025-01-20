package com.bookmyshow_experience.Book_my_show_experience.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Theater;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateTheaterRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.InvalidUserException;
import com.bookmyshow_experience.Book_my_show_experience.utility.MailAPIUtil;

@Service
public class TheaterService {

    DatabaseAPIUtil databaseAPIUtil;
    EntityMapper entityMapper;
    MailAPIUtil mailAPIUtil;

    TheaterService(DatabaseAPIUtil databaseAPIUtil, EntityMapper entityMapper, MailAPIUtil mailAPIUtil) {
        this.databaseAPIUtil = databaseAPIUtil;
        this.entityMapper = entityMapper;
        this.mailAPIUtil = mailAPIUtil;
    }

    public Theater createTheater(CreateTheaterRequestBody createTheaterRequestBody, UUID ownerUserId) {
        // is userID valid? does user id exist in database or not
        // if userId exists then we need to verify whether it is owner or not?
        // call db API to provide user object on the basis of user id we are passing

        AppUser user = databaseAPIUtil.getUserById(ownerUserId);
        if (!user.getUserType().equals("OWNER")) {
            // Exception throw
            throw new InvalidUserException(
                    String.format("User with id %s does not have access to create theater!", ownerUserId.toString()));
        }

        // if you don't come inside if condition that means user is a theater owner
        // experienec api will call database api to create theater in database

        Theater theater = entityMapper.convertTheaterRBToTheaterModel(createTheaterRequestBody, user);
        Theater respTheater = databaseAPIUtil.createTheater(theater);

        // call mail api to notify owner that his theater got registered

        mailAPIUtil.sendTheaterRegistrationMail(theater);

        return respTheater;
    }

}
