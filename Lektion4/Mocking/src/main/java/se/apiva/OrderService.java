package se.apiva;

public class OrderService {

    private InventoryService inventoryService;

    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public Boolean placeOrder(int productId, int quantity){
        return inventoryService.checkAvailability(productId, quantity);
    }

}
