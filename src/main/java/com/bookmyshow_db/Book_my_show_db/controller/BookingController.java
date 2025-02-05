package com.bookmyshow_db.Book_my_show_db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_db.Book_my_show_db.models.Booking;
import com.bookmyshow_db.Book_my_show_db.repository.BookingRepository;

@RestController
@RequestMapping("/api/v1/db/booking")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking) {
        bookingRepository.save(booking);
        return booking;
    }
}
