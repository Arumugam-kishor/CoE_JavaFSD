package warehouseInventoryManagementSystem;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        Product p1 = new Product("P1", "Laptop", 10, new Location(1, 1, 1));
        Product p2 = new Product("P2", "Mouse", 20, new Location(1, 1, 2));
        Product p3 = new Product("P3", "Keyboard", 15, new Location(1, 2, 1));

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);

        List<String> orderItems1 = Arrays.asList("P1", "P2");
        List<String> orderItems2 = Arrays.asList("P2", "P3");

        Order order1 = new Order("O1", orderItems1, Order.Priority.EXPEDITED);
        Order order2 = new Order("O2", orderItems2, Order.Priority.STANDARD);

        manager.submitOrder(order1);
        manager.submitOrder(order2);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        manager.shutdown();
    }
}