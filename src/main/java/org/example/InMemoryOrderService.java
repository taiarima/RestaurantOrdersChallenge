package org.example;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderService implements OrderService {

    private final List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return this.orderList;
    }

    @Override
    public void addOrder(String orderDetails) {
        Order newOrder = new Order(orderDetails);
        orderList.add(newOrder);
        System.out.printf("""
                You successfully added the following order:
                %s
                """, newOrder);
    }

    @Override
    public void markOrderCompleted(int orderId) {

    }

    @Override
    public void editOrderDetails(int orderId, String newDetails) {

    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public void viewAllOrders() {

    }
}
