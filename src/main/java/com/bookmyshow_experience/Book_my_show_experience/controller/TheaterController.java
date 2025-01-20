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

}
