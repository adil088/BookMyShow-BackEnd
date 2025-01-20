package com.bookmyshow_experience.Book_my_show_experience.utility;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Theater;

@Service
public class MailAPIUtil {

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
}
