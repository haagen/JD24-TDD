package se.apiva.hexdemo.domain.service;

import se.apiva.hexdemo.domain.model.*;
import se.apiva.hexdemo.domain.port.Inventory;
import se.apiva.hexdemo.domain.port.Notifier;
import se.apiva.hexdemo.domain.port.PaymentProcessor;
import se.apiva.hexdemo.domain.port.PricingPolicy;

public class CheckoutService {

    private PricingPolicy pricing;
    private Inventory inventory;
    private PaymentProcessor paymentProcessor;
    private Notifier notifier;

    public CheckoutService(
            PricingPolicy pricing,
            Inventory inventory,
            PaymentProcessor paymentProcessor,
            Notifier notifier
    ) {
        this.pricing = pricing;
        this.inventory = inventory;
        this.paymentProcessor = paymentProcessor;
        this.notifier = notifier;
    }

    public Receipt checkout(Cart cart, Customer customer) {

        // 1. Beräkna totalpris - kundspecifika priser (Cart & Kund)
        Money total = pricing.getPrice(cart, customer);

        // 2. Reservera produkter på lagret ( Produkt & Kvantitet)
        for (CartItem i : cart.items()) {
            if(!inventory.reserve(i.productId(), i.quantity())){
                throw new IllegalStateException("Not enough stock for " + i.productId() + " " + i.quantity());
            }
        }

        // 3. Ta betalt av kunden (Kund & Total Belopp)
        String paymentId = paymentProcessor.charge(customer.id(), total);

        // 4. Skicka Order Bekräftelse (Kund, betalning och belopp)
        notifier.orderConfirmation(new OrderConfirmation(
                customer.email(),
                paymentId,
                total
        ));

        // 5. Returnera kvitto (BetalningsId, Total Belopp)
        return new Receipt(paymentId, total);
    }

}
