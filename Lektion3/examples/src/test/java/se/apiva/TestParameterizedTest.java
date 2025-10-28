package se.apiva;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestParameterizedTest {

    // En metod att testa...
    public static boolean isOdd(int n){
        return n % 2 != 0;
    }

    @ParameterizedTest
    @ValueSource(ints = { 3, 1, 9, 5, 33 })
    public void test_odd_numbers(int number) {
        assertTrue(isOdd(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 12, 16, 20, 2, -2 })
    public void test_even_numbers(int number) {
        assertFalse(isOdd(number));
    }


}
