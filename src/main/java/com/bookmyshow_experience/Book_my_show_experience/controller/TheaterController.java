package com.bookmyshow_experience.Book_my_show_experience.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateTheaterRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.services.TheaterService;
import com.bookmyshow_experience.Book_my_show_experience.utility.InvalidUserException;
import com.bookmyshow_experience.Book_my_show_experience.utility.TheaterNotFoundException;
import com.bookmyshow_experience.Book_my_show_experience.utility.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/exp/theater")
public class TheaterController {

    TheaterService theaterService;

    TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createTheater(@RequestBody CreateTheaterRequestBody createTheaterRequestBody,
            @RequestParam UUID ownerUserId) {

        try {
            theaterService.createTheater(createTheaterRequestBody, ownerUserId);
            return new ResponseEntity<>("Theater registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/hall/create")
    public ResponseEntity<String> createHall(@RequestParam UUID theaterId, @RequestParam UUID ownerId,
            @RequestParam int hallSeats) {
        // call service layer
        try {
            theaterService.createHallForTheater(theaterId, ownerId, hallSeats);
            return new ResponseEntity<>("Hall registered successfully", HttpStatus.CREATED);
        } catch (InvalidUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (TheaterNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
