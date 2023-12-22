package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private static int incrementingCounter = 1;
    private final int orderId;
    private String orderDetails;
    private boolean isCompleted;
    private final LocalDateTime creationDateTime;

    public Order(String orderDetails) {
        this.orderDetails = orderDetails;
        this.orderId = incrementingCounter++;
        this.creationDateTime = LocalDateTime.now();
    }

    public static int getIncrementingCounter() {
        return incrementingCounter;
    }
    public String getOrderDetails() {
        return orderDetails;
    }
    public String getFormattedCreationDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        return creationDateTime.format(formatter);
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {

        return """
                Order ID: %d
                Time Order Placed: %s
                Order Details: %s
                Status: %s
                
                """.formatted(this.orderId, this.getFormattedCreationDateTime(), this.orderDetails, (this.isCompleted ? "Complete" : "In Progress"));
    }

}
