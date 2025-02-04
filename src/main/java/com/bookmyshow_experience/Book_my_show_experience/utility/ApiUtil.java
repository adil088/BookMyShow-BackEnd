package com.bookmyshow_experience.Book_my_show_experience.utility;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiUtil {

    public Object makePostCall(String apiUrl, Object requestBody, String endPoint) {
        String url = apiUrl + endPoint;
        URI finalUrl = URI.create(url);

        RequestEntity request = RequestEntity.post(url).body(requestBody);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Object> response = restTemplate.exchange(finalUrl, HttpMethod.POST, request, Object.class);
            return response.getBody();
        } catch (Exception e) {
            throw new DatabaseInsertionException(e.getMessage());
        }
    }

    public Object makeGetCall(String apiUrl, String endPoint) {
        String url = apiUrl + endPoint;
        URI finalUrl = URI.create(url);

        RequestEntity request = RequestEntity.get(url).build();

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Object> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, Object.class);
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

}
