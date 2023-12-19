package org.example;

public class Order {

    private static int incrementingCounter = 1;
    private final int orderId;
    private String orderDetails;
    private boolean isCompleted;

    public Order(String orderDetails) {
        this.orderDetails = orderDetails;
        this.orderId = incrementingCounter++;
    }

    public static int getIncrementingCounter() {
        return incrementingCounter;
    }
    public String getOrderDetails() {
        return orderDetails;
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
                Order Details: %s
                Status: %s
                
                """.formatted(this.orderId, this.orderDetails, (this.isCompleted ? "Complete" : "In Progress"));
    }

}
