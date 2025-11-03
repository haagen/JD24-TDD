package se.apiva.mockbookpro.adapters.persistance;

import org.springframework.stereotype.Component;
import se.apiva.mockbookpro.domain.model.Book;
import se.apiva.mockbookpro.domain.ports.BookRepositoryPort;

@Component
public class BookRepositoryAdapter implements BookRepositoryPort {

    private BookJpaRepository repo;

    public BookRepositoryAdapter(BookJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Book save(Book book) {

        BookJpaEntity bookJpaEntity = new BookJpaEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );

        var saved = repo.save(bookJpaEntity);

        return new Book(saved.getId(), saved.getTitle(), saved.getAuthor(), saved.getYear());
    }
}
