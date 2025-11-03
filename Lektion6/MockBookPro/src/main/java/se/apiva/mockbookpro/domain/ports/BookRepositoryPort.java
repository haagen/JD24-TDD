package se.apiva.mockbookpro.domain.ports;

import se.apiva.mockbookpro.domain.model.Book;

public interface BookRepositoryPort {
    Book save(Book book);
}
