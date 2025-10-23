package se.apiva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {

    private WordCounter wordCounter;

    @BeforeEach
    public void setup() {
        wordCounter = new WordCounter();
    }

    @Test
    public void emptyStringReturnsZero() {
        int result = wordCounter.add("");

        assertEquals(0, result);

    }

    @Test
    public void nullStringReturnsZero() {
        int result = wordCounter.add(null);

        assertEquals(0, result);
    }

    @Test
    public void oneWordReturnsOne() {
        int result = wordCounter.add("one");

        assertEquals(1, result);
    }

    @Test
    public void twoWordsReturnsTwo() {
        int result = wordCounter.add("one two");

        assertEquals(2, result);
    }

    @Test
    public void twoOfTheSameWordReturnsOne() {
        int result = wordCounter.add("one one");

        assertEquals(1, result);
    }

    @Test
    public void twoCallsWithThreeUniqueWordsReturnsThree() {

        wordCounter.add("one two");
        int result = wordCounter.add("two three");

        assertEquals(3, result);
    }

    @Test
    public void casingOfWordShouldNotMatter() {
        assertEquals(2, wordCounter.add("one two One ONE oNE"));
    }

    @Test
    public void wordsWithCommaAndExclamationMarksAreFiltered(){

        assertEquals(2, wordCounter.add("hello,world!"));

    }

    @Test
    public void multipleWordsWithCommasAndExclamationMarksAreFiltered() {
        assertEquals(4, wordCounter.add(",one two three! one, mjau!"));
    }

}
