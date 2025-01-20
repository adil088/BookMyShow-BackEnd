package com.bookmyshow_db.Book_my_show_db.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_db.Book_my_show_db.models.Theater;
import com.bookmyshow_db.Book_my_show_db.repository.TheaterRepository;

@RestController
@RequestMapping("/api/v1/db/theater")
public class TheaterController {

    TheaterRepository theaterRepository;

    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createTheater(@RequestBody Theater theater) {
        theaterRepository.save(theater);
        return new ResponseEntity<>(theater, HttpStatus.CREATED);
    }

}
