package se.apiva;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {

    @Test
    @DisplayName("Accepts a number and returns it as a string")
    public void test_return_number_as_a_string() {
        FizzBuzz fzbz = new FizzBuzz();

        String result = fzbz.fizzBuzz(1);

        assertEquals("1", result);
    }

    @Test
    public void test_return_fizz_for_multiples_of_three() {
        FizzBuzz fzbz = new FizzBuzz();

        String result = fzbz.fizzBuzz(9);

        assertEquals("Fizz", result, "svaret borde vara fizz vid multiple av 3");
    }

    @Test
    public void test_return_buzz_for_multiples_of_five() {
        FizzBuzz fzbz = new FizzBuzz();
        String result = fzbz.fizzBuzz(5);
        assertEquals("Buzz", result);
    }

    @Test
    public void test_return_fizzbuzz_for_multiples_of_five_and_three() {
        FizzBuzz fzbz = new FizzBuzz();
        String result = fzbz.fizzBuzz(15);
        assertEquals("FizzBuzz", result);
    }

}
