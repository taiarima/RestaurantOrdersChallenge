package org.example;

public interface OrderService {
    void addOrder(String orderDetails);
    void markOrderCompleted(int orderId);
    void editOrderDetails(int orderId, String newDetails);
    void deleteOrder(int orderId);
    void viewAllOrders();
}
