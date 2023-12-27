package org.example.service;

import org.example.model.Order;

public interface OrderService {
    void addOrder(String orderDetails);
    void markOrderCompleted(int orderId);
    void editOrderDetails(Order order, String newDetails);
    void deleteOrder(int orderId);
    int viewAllOrders();
    int getNumberOrders();
    Order findOrderById(int orderId);
}
