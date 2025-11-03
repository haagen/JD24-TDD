package se.apiva.mockbookpro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.apiva.mockbookpro.adapters.controllers.BookController;
import se.apiva.mockbookpro.domain.model.Book;
import se.apiva.mockbookpro.domain.service.BookService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookService bookService;

    @Test
    void create_returns_201_and_body() throws Exception {

        // Assemble
        when(bookService.create(
                eq("Cooking Pasta as Italians"),
                eq("Donatello Michello"),
                eq(2002)
        )).thenReturn(new Book(
                10L,
                "Cooking Pasta as Italians",
                "Donatello Michello",
                2002
        ));

        String body = """
                {
                    "title": "Cooking Pasta as Italians",
                    "author": "Donatello Michello",
                    "year": "2002"
                }
                """;

        mockMvc.perform(
                post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(10))
        .andExpect(jsonPath("$.title").value("Cooking Pasta as Italians"));
    }

    @TestConfiguration
    static class BookControllerTestContextConfiguration {
        @Bean
        public BookService bookService() {
            return mock(BookService.class);
        }
    }


}
