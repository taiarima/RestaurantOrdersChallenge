package org.example.service;

import org.example.model.Order;

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
        Order currentOrder = findOrderById(orderId);
        if (currentOrder != null) {
            currentOrder.setCompleted(true);
            System.out.printf("""
                    You have successfully marked order %d as completed.
                    %n""", orderId);
        } else {
            System.out.println("The order ID you have specified does not exist. No action was taken.");
        }
    }

    @Override
    public void editOrderDetails(Order order, String newDetails) {
        order.setOrderDetails(newDetails);
    }

    @Override
    public void deleteOrder(int orderId) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId() == orderId) {
                orderList.remove(i);
                break;
            }
        }
    }

    @Override
    public int viewAllOrders() {
        int numOrders = 0;
        for (Order order : orderList) {
            System.out.print(order.toString());
            numOrders++;
        }

        return numOrders;
    }

    @Override
    public int getNumberOrders() {
        return orderList.size();
    }

    @Override
    public Order findOrderById(int orderId) {
        Order order = null;
        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderId() == orderId) {
                order = currentOrder;
                break;
            }
        }
        return order;
    }
}
