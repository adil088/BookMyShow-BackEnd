package com.bookmyshow_mail.Book_my_show_mail.service;

import org.springframework.stereotype.Service;

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
}
