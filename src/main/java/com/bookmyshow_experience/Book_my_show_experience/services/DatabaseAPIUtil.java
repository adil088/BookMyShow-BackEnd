package com.bookmyshow_experience.Book_my_show_experience.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.DatabaseInsertionException;

@Service
public class DatabaseAPIUtil {

    @Value("${db.api.url}")
    String dbApiUrl;

    public void createUser(CreateUserRequestBody createUserRequestBody) {
        // calling db api to save user

        String finalUrl = dbApiUrl + "/user/create";
        URI url = URI.create(finalUrl);

        // create request entity

        RequestEntity req = RequestEntity.post(url).body(createUserRequestBody);

        // create RestTemplate

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, req, String.class);

        } catch (Exception e) {
            throw new DatabaseInsertionException(e.getMessage());
        }

    }
}
