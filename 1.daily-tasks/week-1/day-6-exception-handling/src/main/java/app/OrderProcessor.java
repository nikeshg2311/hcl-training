package app;

import exception.InsufficientStockException;
import exception.OrderProcessingException;

public class OrderProcessor {

    private int availableStock;

    public OrderProcessor(int availableStock) {
        this.availableStock = availableStock;
    }

    public void processOrder(int quantity)
            throws InsufficientStockException {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Order quantity must be positive."
            );
        }

        if (quantity > availableStock) {
            throw new InsufficientStockException(
                    "Requested " + quantity
                            + " items, but only "
                            + availableStock + " are available."
            );
        }

        availableStock -= quantity;

        System.out.println(
                "Order successful! Quantity: " + quantity
        );

        System.out.println(
                "Remaining stock: " + availableStock
        );
    }

    public int getAvailableStock() {
        return availableStock;
    }
    // Process an order while preserving the original exception
public void processOrderWithAudit(int quantity)
        throws OrderProcessingException {

    try {
        processOrder(quantity);
    } catch (InsufficientStockException e) {
        throw new OrderProcessingException(
                "Order processing failed for quantity "
                        + quantity,
                e
        );
    }
}
}
