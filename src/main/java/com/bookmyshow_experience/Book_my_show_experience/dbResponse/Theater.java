package com.bookmyshow_experience.Book_my_show_experience.dbResponse;

import java.util.UUID;

import lombok.Builder;

@Builder
public class Theater {

    public Theater() {
    }

    public Theater(UUID theaterId, AppUser owner, String address, Long theaterHelpline) {
        TheaterId = theaterId;
        this.owner = owner;
        this.address = address;
        this.theaterHelpline = theaterHelpline;
    }

    UUID TheaterId;
    AppUser owner;
    String address;
    Long theaterHelpline;

    public UUID getTheaterId() {
        return TheaterId;
    }

    public void setTheaterId(UUID theaterId) {
        TheaterId = theaterId;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTheaterHelpline() {
        return theaterHelpline;
    }

    public void setTheaterHelpline(Long theaterHelpline) {
        this.theaterHelpline = theaterHelpline;
    }

}
