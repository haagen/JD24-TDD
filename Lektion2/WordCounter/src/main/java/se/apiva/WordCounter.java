package se.apiva;

import java.util.HashSet;
import java.util.Set;

public class WordCounter {

    Set<String> words = new HashSet<>();

    public int add(String s) {

        if(s == null || s.isEmpty()) {
            return 0;
        }

        s = s.replaceAll("[.,?!;:]", " ").trim();
        for(String word : s.split("\\s+")) {
            words.add(word.toLowerCase());
        }

        return words.size();
    }
}
