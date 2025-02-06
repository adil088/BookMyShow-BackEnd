package com.bookmyshow_experience.Book_my_show_experience.services;

import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Theater;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateTheaterRequestBody;

@Service
public class EntityMapper {

    public Theater convertTheaterRBToTheaterModel(CreateTheaterRequestBody createTheaterRequestBody, AppUser owner) {

        Theater theater = Theater.builder()
                .theaterName(createTheaterRequestBody.getTheaterName())
                .address(createTheaterRequestBody.getAddress())
                .theaterHelpline(createTheaterRequestBody.getHelpLineNumber())
                .owner(owner)
                .build();
        return theater;
    }

}
