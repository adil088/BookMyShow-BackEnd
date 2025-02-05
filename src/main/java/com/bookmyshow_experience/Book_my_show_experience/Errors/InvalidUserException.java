package com.bookmyshow_experience.Book_my_show_experience.Errors;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }
}
