package se.apiva.hexdemo.domain.model;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(amount.subtract(other.amount));
    }

    public Money multiply(Money other) {
        return new Money(amount.multiply(other.amount));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Money m && amount.compareTo(m.amount) == 0;
    }
}
