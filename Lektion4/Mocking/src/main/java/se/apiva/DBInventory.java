package se.apiva;

public class DBInventory implements InventoryService {
    @Override
    public Boolean checkAvailability(int productId, int quantity) {
        // Here we would implement the correct logic and code
        return (productId*2 < quantity);
    }
}
