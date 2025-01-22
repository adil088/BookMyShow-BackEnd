package com.bookmyshow_db.Book_my_show_db.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_db.Book_my_show_db.models.Hall;
import com.bookmyshow_db.Book_my_show_db.models.Theater;
import com.bookmyshow_db.Book_my_show_db.repository.HallRepository;
import com.bookmyshow_db.Book_my_show_db.repository.TheaterRepository;

@RestController
@RequestMapping("/api/v1/db/theater")
public class TheaterController {

    TheaterRepository theaterRepository;
    HallRepository hallRepository;

    public TheaterController(TheaterRepository theaterRepository, HallRepository hallRepository) {
        this.theaterRepository = theaterRepository;
        this.hallRepository = hallRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        theaterRepository.save(theater);
        return new ResponseEntity<>(theater, HttpStatus.CREATED);
    }

    @PostMapping("/hall/create")
    public ResponseEntity<Hall> createHall(@RequestBody Hall hall) {
        hallRepository.save(hall);
        return new ResponseEntity<>(hall, HttpStatus.CREATED);
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity getTheaterById(@PathVariable UUID theaterId) {
        Theater foundTheater = theaterRepository.findById(theaterId).orElse(null);

        if (foundTheater == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foundTheater, HttpStatus.OK);
        }
    }

}
