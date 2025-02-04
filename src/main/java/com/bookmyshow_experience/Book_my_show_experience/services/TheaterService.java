package com.bookmyshow_experience.Book_my_show_experience.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Hall;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Show;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Theater;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateShowRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateTheaterRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.InvalidShowTiming;
import com.bookmyshow_experience.Book_my_show_experience.utility.InvalidUserException;
import com.bookmyshow_experience.Book_my_show_experience.utility.MailAPIUtil;
import com.bookmyshow_experience.Book_my_show_experience.utility.TheaterNotFoundException;
import com.bookmyshow_experience.Book_my_show_experience.utility.UnauthorizedException;

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

    public boolean isOverLapping(List<Show> shows, Show currentShow) {
        for (Show show : shows) {
            if (currentShow.getStartTime() <= show.getEndTime()) {
                return true;
            }
        }
        return false;
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

    public void createHallForTheater(UUID theaterId, UUID ownerId, int hallSeats) {
        // Verify if the user exists or not? and check if he is the owner
        AppUser user = databaseAPIUtil.getUserById(ownerId);

        if (user == null) {
            throw new InvalidUserException(String.format("USER with id %s not found!", ownerId.toString()));
        }

        if (!user.getUserType().equals("OWNER")) {
            throw new UnauthorizedException(
                    String.format("User with id %s does not have permission to create halls", ownerId.toString()));
        }

        // if you don't come inside if condition that means user is a theater owner
        // experienec api will call database api to create theater in database
        Hall hall = new Hall();
        // to create hall object we need 2 things, hallSeats and theater object
        // Exp api has theaterId,
        // we need to develope getTheaterById end point in database api, such that we
        // can get theater by id.

        try {
            Theater theater = databaseAPIUtil.getTheaterById(theaterId);
            if (theater == null) {
                throw new TheaterNotFoundException(
                        String.format("Theater with id %s does not exist", theaterId.toString()));
            }
            if (!theater.getOwner().getId().equals(ownerId)) {
                throw new UnauthorizedException(
                        String.format("User with id %s does not have access to create hall in theater with id %s",
                                ownerId.toString(), theaterId.toString()));
            }
            hall.setTheater(theater);
            hall.setSeats(hallSeats);
            hall = databaseAPIUtil.createHall(hall);
        } catch (Exception e) {
            throw e;
        }

        // Mail api -> notify hall owner that his hall has been created for this
        // particular theater

        try {
            mailAPIUtil.sendHallRegistrationMail(hall);

        } catch (Exception e) {
            throw e;
        }

    }

    // create show in hall in theater

    public Show createShow(UUID userId, UUID theaterId, UUID hallId,
            CreateShowRequestBody show) {
        AppUser user = databaseAPIUtil.getUserById(userId);

        if (user == null) {
            throw new InvalidUserException(String.format("USER with id %s not found!", userId.toString()));
        }

        if (!user.getUserType().equals("OWNER")) {
            throw new UnauthorizedException(
                    String.format("User with id %s does not have permission to create halls", userId.toString()));
        }
        Theater theater = databaseAPIUtil.getTheaterById(theaterId);
        if (theater == null) {
            throw new TheaterNotFoundException(
                    String.format("Theater with id %s does not exist", theaterId.toString()));
        }
        if (!theater.getOwner().getId().equals(userId)) {
            throw new UnauthorizedException(
                    String.format("User with id %s does not have access to create hall in theater with id %s",
                            userId.toString(), theaterId.toString()));
        }

        Hall hall = databaseAPIUtil.getHallById(hallId);

        LocalDateTime startTime = show.getStartTime();
        LocalDateTime endTime = show.getEndTime();

        LocalDateTime fromTime = LocalDateTime.of(2010, 12, 1, 7, 00, 00);

        Long startInSeconds = Duration.between(fromTime, startTime).toSeconds();
        Long endInSeconds = Duration.between(fromTime, endTime).toSeconds();

        // call database api util, it will create show for us
        List<Show> shows = databaseAPIUtil.getAllShows();
        Collections.sort(shows);
        Show showRB = new Show();
        showRB.setHall(hall);
        showRB.setStartTime(startInSeconds);
        showRB.setEndTime(endInSeconds);
        showRB.setMovieName(show.getMovieName());
        showRB.setTicketsSold(20);
        System.out.println(showRB);
        boolean result = isOverLapping(shows, showRB);
        if (result) {
            throw new InvalidShowTiming("Show timing is overlapping");
        }

        return databaseAPIUtil.createShow(showRB);
    }

}
