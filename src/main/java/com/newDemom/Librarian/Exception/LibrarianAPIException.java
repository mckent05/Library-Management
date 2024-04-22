package com.newDemom.Librarian.Exception;

import org.springframework.http.HttpStatus;

public class LibrarianAPIException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public LibrarianAPIException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
