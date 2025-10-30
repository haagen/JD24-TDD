package se.apiva.hexdemo.adapter.inventory.jpa;

import org.springframework.stereotype.Component;
import se.apiva.hexdemo.domain.model.ProductId;
import se.apiva.hexdemo.domain.port.Inventory;

@Component
public class JpaInventory implements Inventory {

    private final StockRepository stockRepository;
    public JpaInventory(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public boolean reserve(ProductId productId, int quantity) {
        return stockRepository.findById(productId.value()).map(
                entity -> {
                    if(entity.getQuantity() < quantity){
                        return false;
                    }
                    entity.setQuantity(entity.getQuantity() - quantity);
                    return true;
                }).orElse(false);
    }
}
