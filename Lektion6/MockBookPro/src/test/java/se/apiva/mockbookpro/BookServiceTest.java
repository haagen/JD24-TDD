package se.apiva.mockbookpro;

import org.junit.jupiter.api.Test;
import se.apiva.mockbookpro.domain.ports.BookRepositoryPort;
import se.apiva.mockbookpro.domain.service.BookService;
import se.apiva.mockbookpro.domain.model.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {


    @Test
    public void create_saves_and_returns_with_id() {

        // Assemble
        BookRepositoryPort repo = mock(BookRepositoryPort.class);
        when(repo.save(any())).thenReturn(new Book(
            42L,
            "Test-driven utveckling med Java",
            "Bob Hund",
            2024
        ));
        BookService bookService = new BookService(repo);

        // Act
        var saved = bookService.create(
                "Test-driven utveckling med Java",
                "Bob Hund",
                2024
        );

        // Assert
        assertAll(
                "Saved book has the right values",
                () -> assertNotNull(saved.getId()),
                () -> assertEquals(42L, saved.getId()),
                () -> assertEquals("Test-driven utveckling med Java", saved.getTitle()),
                () -> assertEquals("Bob Hund", saved.getAuthor()),
                () -> assertEquals(2024, saved.getYear())
        );

    }
}
