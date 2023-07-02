package org.example.exception;

public class IncorrectQueryException extends RuntimeException{

    public IncorrectQueryException(String message) {
        super(message);
    }

}
