package com.bookmyshow_experience.Book_my_show_experience.Errors;

public class NotEnoughSeatsException extends RuntimeException {
    public NotEnoughSeatsException(String message) {
        super(message);
    }
}
