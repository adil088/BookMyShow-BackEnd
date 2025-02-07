package com.bookmyshow_experience.Book_my_show_experience.dbResponse;

import java.util.UUID;

import lombok.Builder;

@Builder
public class Hall {

    UUID id;
    String hallName;
    int seats;
    Theater theater;

    public Hall() {
    }

    public Hall(UUID id, String hallName, int seats, Theater theater) {
        this.id = id;
        this.hallName = hallName;
        this.seats = seats;
        this.theater = theater;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

}
