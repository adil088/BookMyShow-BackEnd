package com.bookmyshow_db.Book_my_show_db.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_db.Book_my_show_db.models.Hall;
import com.bookmyshow_db.Book_my_show_db.repository.HallRepository;

@RestController
@RequestMapping("/api/v1/db/hall")
public class HallController {

    @Autowired
    HallRepository hallRepository;

    @GetMapping("/{hallId}")
    public Hall getHallById(@PathVariable UUID hallId) {
        return hallRepository.findById(hallId).orElse(null);
    }

}
