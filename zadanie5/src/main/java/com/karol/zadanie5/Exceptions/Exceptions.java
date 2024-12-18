package com.karol.zadanie5.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exceptions {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
    }
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public static class CannotAddResourceException extends RuntimeException {
    }
}