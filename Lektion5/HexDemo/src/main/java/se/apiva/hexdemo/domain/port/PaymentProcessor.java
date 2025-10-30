package se.apiva.hexdemo.domain.port;

import se.apiva.hexdemo.domain.model.CustomerId;
import se.apiva.hexdemo.domain.model.Money;
import se.apiva.hexdemo.domain.model.ProductId;

public interface PaymentProcessor {
    String charge(CustomerId customerId, Money amount);
}
