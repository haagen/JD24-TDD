package se.apiva.mockbookpro.adapters.controllers.dto;

public record BookRequest(
        String title,
        String author,
        int year
) { }
