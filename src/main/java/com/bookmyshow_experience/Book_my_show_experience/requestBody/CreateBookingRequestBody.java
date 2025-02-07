package com.bookmyshow_experience.Book_my_show_experience.requestBody;

import java.util.UUID;

import com.bookmyshow_experience.Book_my_show_experience.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBookingRequestBody {
    UUID showId;
    int totalSeats;
    PaymentMethod paymentMode;
}
