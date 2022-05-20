package com.polarbookshop.bookservice.domain;

public record Book (
    String isbn,
    String title,
    String author,
    Double price
){}
