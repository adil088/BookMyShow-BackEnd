package com.bookmyshow_experience.Book_my_show_experience.Errors;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
