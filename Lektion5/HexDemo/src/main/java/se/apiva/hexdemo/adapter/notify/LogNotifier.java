package se.apiva.hexdemo.adapter.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.apiva.hexdemo.domain.model.OrderConfirmation;
import se.apiva.hexdemo.domain.port.Notifier;

@Component
public class LogNotifier implements Notifier {

    private static final Logger log = LoggerFactory.getLogger(LogNotifier.class);

    @Override
    public void orderConfirmation(OrderConfirmation confirm) {
        log.info("Order confirmed: {}", confirm);
    }
}
