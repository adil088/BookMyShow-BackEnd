package com.bookmyshow_db.Book_my_show_db.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_db.Book_my_show_db.models.Show;
import com.bookmyshow_db.Book_my_show_db.repository.ShowRepository;

@RestController
@RequestMapping("/api/v1/db/show")
public class ShowController {

    ShowRepository showRepository;

    ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        showRepository.save(show);

        return new ResponseEntity<Show>(show, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @GetMapping("/{showId}")
    public Show getShowById(@PathVariable UUID showId) {
        return showRepository.findById(showId).orElse(null);
    }
}
