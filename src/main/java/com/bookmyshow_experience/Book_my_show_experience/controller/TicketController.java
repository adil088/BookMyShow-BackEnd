package com.bookmyshow_experience.Book_my_show_experience.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Booking;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateBookingRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/exp/ticket")
public class TicketController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/book")
    public Booking bookTicket(@RequestBody CreateBookingRequestBody createBookingRequestBody) {

        Booking booking = bookingService.bookTicket(createBookingRequestBody);
        return booking;
    }

}
