package se.apiva;

public class SafeCalculator implements Calculator {
    @Override
    public int add(int a, int b) {
        return Math.addExact(a, b);
    }
}
