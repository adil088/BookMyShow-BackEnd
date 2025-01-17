package com.bookmyshow_mail.Book_my_show_mail.service;

import org.springframework.stereotype.Service;

import com.bookmyshow_mail.Book_my_show_mail.utility.MailUtility;

@Service
public class UserService {

    MailUtility mailUtility;

    UserService(MailUtility mailUtility) {
        this.mailUtility = mailUtility;
    }

    public void sendUserRegistrationMail(String email, String userName) throws Exception {
        mailUtility.sendMail("Welcome to BookMyShow", "user-registration", email, userName);
    }
}
