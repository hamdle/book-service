package com.polarbookshop.bookservice.domain;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("The book with ISBN " + " was not found.");
    }
}
