package se.apiva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAssertAll {

    @Test
    public void testBookPojo() {

        Book myBook = new Book(
            "Test Driven Development with Java",
            "Allan Mellor",
            2024
        );

        assertAll(
                "Testing Book Properties",
                () -> assertEquals("Test Driven Development with Java", myBook.getTitle()),
                () -> assertEquals(2024, myBook.getYear()),
                () -> assertEquals("Allan Mellor", myBook.getAuthor())
        );

    }

}
