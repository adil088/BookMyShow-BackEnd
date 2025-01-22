package com.bookmyshow_experience.Book_my_show_experience.dbResponse;

import java.util.UUID;

import lombok.Builder;

@Builder
public class Hall {

    UUID id;
    Theater theater;
    int seats;

    public Hall() {
    }

    public Hall(UUID id, Theater theater, int seats) {
        this.id = id;
        this.theater = theater;
        this.seats = seats;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}
