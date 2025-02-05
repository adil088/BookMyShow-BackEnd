package com.bookmyshow_experience.Book_my_show_experience.dbResponse;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {
    UUID id;
    AppUser user;
    int totalSeats;
    Show show;
    String paymentMethod;
    int totalAmount;
}
