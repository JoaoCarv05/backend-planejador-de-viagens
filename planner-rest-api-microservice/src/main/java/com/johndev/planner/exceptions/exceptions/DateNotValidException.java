package com.johndev.planner.exceptions.exceptions;

public class DateNotValidException extends RuntimeException{
    public DateNotValidException(String message) {
        super(message);
    }

    public DateNotValidException() {
        super("Invalid date Type");
    }
}
