package com.johndev.planner.exceptions.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super("Entity not found in database, read the Documentation.");
    }
}