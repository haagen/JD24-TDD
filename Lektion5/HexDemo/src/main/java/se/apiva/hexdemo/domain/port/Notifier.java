package se.apiva.hexdemo.domain.port;

import se.apiva.hexdemo.domain.model.OrderConfirmation;

public interface Notifier {

    void orderConfirmation(OrderConfirmation confirm);

}
