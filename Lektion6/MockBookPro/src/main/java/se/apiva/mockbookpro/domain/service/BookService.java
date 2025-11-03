package se.apiva.mockbookpro.domain.service;

import se.apiva.mockbookpro.domain.model.Book;
import se.apiva.mockbookpro.domain.ports.BookRepositoryPort;

public class BookService {

    BookRepositoryPort bookRepository;

    public BookService(BookRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(String title, String author, int year) {
        return bookRepository.save(new Book(
            null,
            title,
            author,
            year
        ));
    }
}
