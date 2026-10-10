package app;

import exception.InsufficientStockException;
import exception.OrderProcessingException;

public class OrderProcessorApp {

    public static void main(String[] args) {

        OrderProcessor processor = new OrderProcessor(10);

        System.out.println("===== ORDER 1 =====");

        try {
            processor.processOrder(4);
        } catch (InsufficientStockException e) {
            System.out.println("Order failed: " + e.getMessage());
        } finally {
            System.out.println("Order 1 processing completed.");
        }

        System.out.println("\n===== ORDER 2 =====");

        try {
            processor.processOrder(20);
        } catch (InsufficientStockException e) {
            System.out.println("Order failed: " + e.getMessage());
        } finally {
            System.out.println("Order 2 processing completed.");
        }

        System.out.println("\n===== ORDER 3 =====");

        try {
            processor.processOrder(3);
        } catch (InsufficientStockException e) {
            System.out.println("Order failed: " + e.getMessage());
        } finally {
            System.out.println("Order 3 processing completed.");
        }

        System.out.println("\n===== FINAL STOCK =====");
        System.out.println(processor.getAvailableStock());

        // Test exception chaining
        System.out.println("\n===== EXCEPTION CHAINING =====");

        try {
            processor.processOrderWithAudit(50);
        } catch (OrderProcessingException e) {
            System.out.println("Main error: " + e.getMessage());

            System.out.println(
                    "Original cause: " + e.getCause().getMessage()
            );

            System.out.println("\n===== STACK TRACE =====");
            e.printStackTrace(System.out);
        }
    }
}
