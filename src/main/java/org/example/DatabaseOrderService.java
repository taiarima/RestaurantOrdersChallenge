package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOrderService implements OrderService {
    @Override
    public void addOrder(String orderDetails) {
        String sql = "INSERT INTO orders (order_details) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, orderDetails);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 1) {

                System.out.println("Order added successfully");
            } else {
                System.out.println("Order was not added");
            }
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    @Override
    public void markOrderCompleted(int orderId) {

    }

    @Override
    public void editOrderDetails(Order order, String newDetails) {

    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public void viewAllOrders() {

    }

    @Override
    public int getNumberOrders() {
        return 0;
    }

    @Override
    public Order findOrderById(int orderId) {
        return null;
    }
}
