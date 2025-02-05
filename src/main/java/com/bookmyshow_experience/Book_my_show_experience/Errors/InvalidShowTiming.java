package com.bookmyshow_experience.Book_my_show_experience.Errors;

public class InvalidShowTiming extends RuntimeException {
    public InvalidShowTiming(String message) {
        super(message);
    }
}
