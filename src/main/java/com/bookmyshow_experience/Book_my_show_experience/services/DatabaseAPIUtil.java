package com.bookmyshow_experience.Book_my_show_experience.services;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Hall;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Show;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Theater;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateUserRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.ApiUtil;
import com.bookmyshow_experience.Book_my_show_experience.utility.DatabaseInsertionException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DatabaseAPIUtil extends ApiUtil {

    @Value("${db.api.url}")
    String dbApiUrl;

    public void createUser(CreateUserRequestBody createUserRequestBody) {
        // calling db api to save user
        System.out.println("Calling db api to create user");
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

    public AppUser getUserById(UUID id) {
        String url = dbApiUrl + "/user/" + id.toString();
        URI finalUrl = URI.create(url);

        RequestEntity request = RequestEntity.get(url).build();

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<AppUser> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, AppUser.class);
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    public Theater createTheater(Theater theater) {
        String finalUrl = dbApiUrl + "/theater/create";
        URI url = URI.create(finalUrl);

        // create request entity
        RequestEntity req = RequestEntity.post(url).body(theater);

        // create RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Theater> response = restTemplate.exchange(url, HttpMethod.POST, req, Theater.class);
            return response.getBody();
        } catch (Exception e) {
            throw new DatabaseInsertionException(e.getMessage());
        }
    }

    public Hall createHall(Hall hall) {
        // 1. Create URL
        String url = dbApiUrl + "/theater/hall/create";
        URI finalUrl = URI.create(url);

        // 2. Create request entity
        RequestEntity<Hall> request = RequestEntity.post(url).body(hall);

        // 3.Hit the request with rest template, create rest template
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Hall> response = restTemplate.exchange(finalUrl, HttpMethod.POST, request, Hall.class);
            return response.getBody();
        } catch (Exception e) {
            throw new DatabaseInsertionException(e.getMessage());
        }
    }

    public Theater getTheaterById(UUID theaterId) {
        String url = dbApiUrl + "/theater/" + theaterId.toString();
        URI finalUrl = URI.create(url);

        RequestEntity request = RequestEntity.get(url).build();

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Theater> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, Theater.class);
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    public AppUser getUserByEmail(String email) {
        String url = dbApiUrl + "/user/email/" + email;
        URI finalUrl = URI.create(url);

        RequestEntity request = RequestEntity.get(url).build();

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<AppUser> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, AppUser.class);
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    public Show createShow(Show show) {
        Object resp = makePostCall(dbApiUrl, show, "/show/create");
        ModelMapper mapper = new ModelMapper();
        Show showResp = mapper.map(resp, Show.class);
        return showResp;
    }

    public Hall getHallById(UUID hallId) {
        String endPoint = "/hall/" + hallId.toString();
        Object resp = makeGetCall(dbApiUrl, endPoint);
        ModelMapper mapper = new ModelMapper();
        Hall hall = mapper.map(resp, Hall.class);
        return hall;
    }

    public List<Show> getAllShows() {
        String endPoint = "/show/all";
        ModelMapper mapper = new ModelMapper();
        Type listType = new TypeToken<List<Show>>() {
        }.getType();
        Object resp = makeGetCall(dbApiUrl, endPoint);
        List<Show> shows = mapper.map(resp, listType);
        return shows;
    }
}