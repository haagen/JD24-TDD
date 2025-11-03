package se.apiva.mockbookpro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void create_book() {

        String url = "http://localhost:" + port + "/api/books";
        var req = Map.of("title", "TITLE", "author", "AUTHOR", "year", 2004);
        ResponseEntity<Map> created = restTemplate.postForEntity(
                URI.create(url),
                req,
                Map.class
        );
        assertEquals(HttpStatus.CREATED, created.getStatusCode());
        Integer id = (Integer) created.getBody().get("id");
        assertNotNull(id);
        System.out.println("MARTIN!!");
        System.out.println(id);
    }

}
