package warehouseInventoryManagementSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InventoryManager {
    private final Map<String, Product> products;
    private final PriorityQueue<Order> orderQueue;
    private final ExecutorService orderProcessorPool;
    private static final Logger logger = Logger.getLogger(InventoryManager.class.getName());
    private final String DATA_FILE = "inventory_data.txt";

    public InventoryManager() {
        this.products = new ConcurrentHashMap<>();
        this.orderQueue = new PriorityQueue<>();
        this.orderProcessorPool = Executors.newFixedThreadPool(5);
        loadInventoryData();
    }

    public void addProduct(Product product) {
        products.put(product.getProductID(), product);
        logger.info("Added product: " + product.getProductID());
        saveInventoryData();
    }

    public void submitOrder(Order order) {
        synchronized (orderQueue) {
            orderQueue.offer(order);
            logger.info("Order submitted: " + order.getOrderID());
        }
        processOrders();
    }

    private void processOrders() {
        orderProcessorPool.submit(() -> {
            while (true) {
                Order order;
                synchronized (orderQueue) {
                    order = orderQueue.poll();
                    if (order == null) break;
                }

                try {
                    processOrder(order);
                } catch (Exception e) {
                    logger.severe("Error processing order " + order.getOrderID() + ": " + e.getMessage());
                }
            }
        });
    }

    private void processOrder(Order order) throws OutOfStockException {
        List<Product> orderedProducts = new ArrayList<>();
        Map<Location, List<Product>> pickingMap = new TreeMap<>();

        for (String productID : order.getProductIDs()) {
            Product product = products.get(productID);
            if (product == null) {
                throw new IllegalArgumentException("Product not found: " + productID);
            }
            if (product.getQuantity() <= 0) {
                throw new OutOfStockException("Product out of stock: " + productID);
            }
            orderedProducts.add(product);
            pickingMap.computeIfAbsent(product.getLocation(), k -> new ArrayList<>()).add(product);
        }

        for (Product product : orderedProducts) {
            product.updateQuantity(-1);
        }

        pickingMap.forEach((location, productList) -> {
            logger.info("Picking at " + location + ": " + 
                       productList.stream()
                                .map(Product::getProductID)
                                .collect(Collectors.joining(", ")));
        });

        logger.info("Completed order: " + order.getOrderID());
        saveInventoryData();
    }

    private void loadInventoryData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Map<String, Product> loadedProducts = (Map<String, Product>) ois.readObject();
            products.putAll(loadedProducts);
            logger.info("Loaded inventory data from file");
        } catch (IOException | ClassNotFoundException e) {
            logger.warning("Could not load inventory data: " + e.getMessage());
        }
    }

    private void saveInventoryData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(products);
            logger.info("Saved inventory data to file");
        } catch (IOException e) {
            logger.severe("Could not save inventory data: " + e.getMessage());
        }
    }

    public void shutdown() {
        orderProcessorPool.shutdown();
        try {
            if (!orderProcessorPool.awaitTermination(60, TimeUnit.SECONDS)) {
                orderProcessorPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            orderProcessorPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        saveInventoryData();
    }
}