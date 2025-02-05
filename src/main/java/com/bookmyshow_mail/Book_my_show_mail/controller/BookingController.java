package com.bookmyshow_mail.Book_my_show_mail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_mail.Book_my_show_mail.requestBody.Booking;
import com.bookmyshow_mail.Book_my_show_mail.utility.MailUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/mail/booking")
public class BookingController {

    @Autowired
    MailUtility mailUtility;

    @PutMapping("/create")
    public void sendBookingMail(@RequestBody Booking booking) throws Exception {
        mailUtility.sendBookingMail(booking);
    }
}
