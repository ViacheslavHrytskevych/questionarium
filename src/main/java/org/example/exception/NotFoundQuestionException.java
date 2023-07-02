package org.example.exception;

public class NotFoundQuestionException extends RuntimeException{

    public NotFoundQuestionException(String message) {
        super(message);
    }
}
