package se.apiva;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(CalculatorInvocationProvider.class)
public class TestCalculators {

    @TestTemplate
    public void addNumbersCorrectly(Calculator calc) {

        final int FIRST_NUMBER = 2;
        final int SECOND_NUMBER = 8;

        int result = calc.add(FIRST_NUMBER, SECOND_NUMBER);

        assertEquals(10, result);
    }


    // OBS! Detta testet kommer att fallera för "Safe Calculator"
    @TestTemplate
    public void testOverflow(Calculator calc) {
        final int FIRST_NUMBER = Integer.MAX_VALUE - 1;
        final int SECOND_NUMBER = 2;

        int result = calc.add(FIRST_NUMBER, SECOND_NUMBER);

        assertEquals(Integer.MIN_VALUE, result);
    }
}



