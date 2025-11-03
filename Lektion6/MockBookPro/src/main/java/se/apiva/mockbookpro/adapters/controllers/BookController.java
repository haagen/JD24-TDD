package se.apiva.mockbookpro.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.apiva.mockbookpro.adapters.controllers.dto.BookRequest;
import se.apiva.mockbookpro.adapters.controllers.dto.BookResponse;
import se.apiva.mockbookpro.domain.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@RequestBody BookRequest body) {
        var savedBook = bookService.create(body.title(), body.author(), body.year());
        return new BookResponse(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getAuthor(),
                savedBook.getYear()
        );
    }

}
