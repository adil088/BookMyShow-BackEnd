package com.bookmyshow_mail.Book_my_show_mail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_mail.Book_my_show_mail.requestBody.Theater;
import com.bookmyshow_mail.Book_my_show_mail.service.TheaterService;

@RestController
@RequestMapping("/api/v1/mail/theater")
public class TheaterController {

    TheaterService theaterService;

    TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PutMapping("/create")
    public ResponseEntity sendTheaterRegistrationMail(
            @RequestBody Theater theater) {

        try {
            theaterService.sendTheaterRegistrationMail(theater);
            return new ResponseEntity<>("Mail sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Mail failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
