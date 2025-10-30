package se.apiva.hexdemo.domain.model;

public record OrderConfirmation(
        String email,
        String paymentId,
        Money total
) { }
