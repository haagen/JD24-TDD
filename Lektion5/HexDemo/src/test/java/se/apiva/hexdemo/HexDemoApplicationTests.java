package se.apiva.hexdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.apiva.hexdemo.domain.model.*;
import se.apiva.hexdemo.domain.port.Inventory;
import se.apiva.hexdemo.domain.port.Notifier;
import se.apiva.hexdemo.domain.port.PaymentProcessor;
import se.apiva.hexdemo.domain.port.PricingPolicy;
import se.apiva.hexdemo.domain.service.CheckoutService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class HexDemoApplicationTests {

    @Test
    void contextLoads() {

        // Assemble
        Inventory inventory = mock(Inventory.class);
        Notifier notifier = mock(Notifier.class);
        PaymentProcessor paymentProcessor = mock(PaymentProcessor.class);
        PricingPolicy pricingPolicy = mock(PricingPolicy.class);
        CheckoutService checkoutService = new CheckoutService(
            pricingPolicy,
            inventory,
            paymentProcessor,
            notifier
        );
        when(inventory.reserve(new ProductId("PRODUKT_ID"), 10)).thenReturn(true);
        when(paymentProcessor.charge(any(), any())).thenReturn("PAYMENT-ID");
        when(pricingPolicy.getPrice(any(), any())).thenReturn(new Money(BigDecimal.TEN));
        Cart cart = new Cart(
                List.of(
                    new CartItem(
                            new ProductId("PRODUKT_ID"),
                            new Money(BigDecimal.valueOf(125.25f)),
                            10
                    )
                )
        );
        Customer customer = new Customer(
                new CustomerId("KUND-ID"),
                "test@email.se"
        );

        // Act
        Receipt receipt = checkoutService.checkout(cart, customer);

        // Assert
        assertAll(
                "Receipt validation",
                () -> assertTrue(receipt.total().equals(new Money(BigDecimal.TEN))),
                () -> assertEquals("PAYMENT-ID", receipt.paymentId())
        );

    }

}
