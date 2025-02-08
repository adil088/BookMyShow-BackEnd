package com.bookmyshow_experience.Book_my_show_experience.Errors;

public class WrongCredentialsException extends RuntimeException {
    WrongCredentialsException(String message) {
        super(message);
    }
}
