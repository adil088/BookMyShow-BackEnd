package com.bookmyshow_experience.Book_my_show_experience.utility;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Booking;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Hall;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Theater;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.BookingRBforEmail;

@Service
public class MailAPIUtil extends ApiUtil {

    @Value("${mail.api.url}")
    String mailApiUrl;

    public void sendUserRegistrationEmail(String userEmail, String userName) {

        String url = mailApiUrl + "/user/register" + "?email=" + userEmail + "&userName=" + userName;
        URI finalUrl = URI.create(url);

        RequestEntity req = RequestEntity.put(finalUrl).build();

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, req, String.class);
        } catch (Exception e) {
            throw e;
        }

    }

    public void sendTheaterRegistrationMail(Theater theater) {
        String url = mailApiUrl + "/theater/create";
        URI finalUrl = URI.create(url);

        RequestEntity req = RequestEntity.put(finalUrl).body(theater);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, req, String.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public void sendHallRegistrationMail(Hall hall) {
        String url = mailApiUrl + "/theater/hall/create";
        URI finalUrl = URI.create(url);

        RequestEntity req = RequestEntity.put(finalUrl).body(hall);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, req, String.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public void sendBookingMail(Booking booking) {
        BookingRBforEmail bookingRBforEmail = new BookingRBforEmail();
        bookingRBforEmail.setHallName("Hall1");
        bookingRBforEmail.setPaymentMethod(booking.getPaymentMethod());
        bookingRBforEmail.setTotalTickets(booking.getTotalSeats());
        bookingRBforEmail.setUserEmail(booking.getUser().getEmail());
        bookingRBforEmail.setTheaterName("Theater1");
        bookingRBforEmail.setTotalAmountPaid(booking.getTotalAmount());
        bookingRBforEmail.setTheaterAddress(booking.getShow().getHall().getTheater().getAddress());
        bookingRBforEmail.setUserName(booking.getUser().getEmail());

        makePutCall(mailApiUrl, bookingRBforEmail, "/booking/create");
    }
}
