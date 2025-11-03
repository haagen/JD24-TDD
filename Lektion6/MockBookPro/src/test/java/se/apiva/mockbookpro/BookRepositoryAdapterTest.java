package se.apiva.mockbookpro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import se.apiva.mockbookpro.adapters.persistance.BookJpaRepository;
import se.apiva.mockbookpro.adapters.persistance.BookRepositoryAdapter;
import se.apiva.mockbookpro.domain.model.Book;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryAdapter.class)
@ActiveProfiles("test")
public class BookRepositoryAdapterTest {

    @Autowired
    BookJpaRepository repo;

    @Autowired
    BookRepositoryAdapter adapter;

    @Test
    void save_and_query() {
        Book toSave = new Book(null, "TITLE", "AUTHOR", 2002);
        Book saved = adapter.save(toSave);
        assertNotNull(saved.getId());

        var found = repo.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("TITLE", found.get().getTitle());
    }


}
