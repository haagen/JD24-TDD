package se.apiva.mockbookpro.adapters.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.apiva.mockbookpro.domain.ports.BookRepositoryPort;
import se.apiva.mockbookpro.domain.service.BookService;

@Configuration
public class ServiceConfig {

    @Bean
    BookService bookService(BookRepositoryPort bookRepositoryPort){
        return new BookService(bookRepositoryPort);
    }

}
