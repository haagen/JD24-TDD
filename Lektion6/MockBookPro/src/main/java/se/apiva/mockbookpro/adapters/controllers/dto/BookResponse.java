package se.apiva.mockbookpro.adapters.controllers.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        int year
) { }
