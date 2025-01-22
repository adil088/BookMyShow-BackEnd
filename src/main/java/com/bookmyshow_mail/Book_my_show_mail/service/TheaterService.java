package com.bookmyshow_mail.Book_my_show_mail.service;

import org.springframework.stereotype.Service;

import com.bookmyshow_mail.Book_my_show_mail.requestBody.Hall;
import com.bookmyshow_mail.Book_my_show_mail.requestBody.Theater;
import com.bookmyshow_mail.Book_my_show_mail.utility.MailUtility;

@Service
public class TheaterService {
    MailUtility mailUtility;

    TheaterService(MailUtility mailUtility) {
        this.mailUtility = mailUtility;
    }

    public void sendTheaterRegistrationMail(Theater theater) throws Exception {
        String toEmail = theater.getOwner().getEmail();
        String ownerName = theater.getOwner().getName();
        String address = theater.getAddress();
        String subjectLine = "Theater Registration Successful!";

        mailUtility.sendTheaterRegistrationMail(toEmail, ownerName, address, subjectLine);
    }

    public void sendHallRegistrationMail(Hall hall) throws Exception {
        String ownerEmail = hall.getTheater().getOwner().getEmail();
        String ownerName = hall.getTheater().getOwner().getName();
        String theaterLocation = hall.getTheater().getAddress();
        String subjectLine = "Hall Registration Successful!";
        int seats = hall.getSeats();

        mailUtility.sendHallRegistrationMail(ownerEmail, ownerName, theaterLocation, subjectLine, seats);
    }
}
