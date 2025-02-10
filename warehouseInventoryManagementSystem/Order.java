package warehouseInventoryManagementSystem;

import java.time.LocalDateTime;
import java.util.List;

public class Order implements Comparable<Order> {
    private String orderID;
    private List<String> productIDs;
    private Priority priority;
    private LocalDateTime timestamp;

    public enum Priority {
        STANDARD(0),
        EXPEDITED(1);

        private final int value;
        Priority(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    public Order(String orderID, List<String> productIDs, Priority priority) {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public int compareTo(Order other) {
        int priorityCompare = Integer.compare(other.priority.getValue(), this.priority.getValue());
        return priorityCompare != 0 ? priorityCompare : this.timestamp.compareTo(other.timestamp);
    }

    public String getOrderID() { return orderID; }
    public List<String> getProductIDs() { return productIDs; }
    public Priority getPriority() { return priority; }
}