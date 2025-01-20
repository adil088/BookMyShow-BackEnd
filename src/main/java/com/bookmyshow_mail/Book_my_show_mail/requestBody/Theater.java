package com.bookmyshow_mail.Book_my_show_mail.requestBody;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

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
