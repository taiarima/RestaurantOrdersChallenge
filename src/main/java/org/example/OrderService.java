package org.example;

public interface OrderService {
    void addOrder(String orderDetails);
    void markOrderCompleted(int orderId);
    void editOrderDetails(Order order, String newDetails);
    void deleteOrder(int orderId);
    void viewAllOrders();
    int getNumberOrders();
    Order findOrderById(int orderId);
}
