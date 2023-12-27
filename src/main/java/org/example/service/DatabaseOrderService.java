package org.example.service;

import org.example.util.HibernateUtil;
import org.example.model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DatabaseOrderService implements OrderService {
    @Override
    public void addOrder(String orderDetails) {
        System.out.println("Processing request...");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Order newOrder = new Order(orderDetails);
            session.save(newOrder);
            transaction.commit();
            System.out.printf("""
                You successfully added the following order:
                %s
                """, newOrder);
        } catch (Exception e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    @Override
    public void markOrderCompleted(int orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Order ID has already been validated in handler method
            Order order = session.get(Order.class, orderId);
            order.setCompleted(true);
            session.update(order);

            transaction.commit();
            System.out.println("Order marked as completed successfully.");

        } catch (Exception e) {
            System.out.println("Could not mark order as completed. Error: " + e.getMessage());
        }
    }

    @Override
    public void editOrderDetails(Order order, String newDetails) {

    }

    @Override
    public void deleteOrder(int orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Order order = session.get(Order.class, orderId);
            if (order != null) {
                session.delete(order);
                transaction.commit();
                System.out.println("Order with ID " + orderId + " was successfully deleted.");
            } else {
                System.out.println("Order with ID " + orderId + " not found.");
                transaction.rollback();
            }

        } catch (Exception e) {
            System.out.println("Unable to process request. Error: " + e.getMessage());
        }
    }


    @Override
    public int viewAllOrders() {
        int numOrders = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setDefaultReadOnly(true);

            List<Order> orders = session.createQuery("FROM Order", Order.class).list();

            for (Order order : orders) {
                System.out.print(order.toString());
                numOrders++;
            }

            return numOrders;
        } catch (Exception e) {
            System.out.println("Operation failed. Error message: " + e.getMessage());
        }
        return numOrders;
    }


    @Override
    public int getNumberOrders() {
        int numOrders = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setDefaultReadOnly(true);
            numOrders = ((Long) session.createQuery("SELECT COUNT(o) FROM Order o").uniqueResult()).intValue();
        } catch (Exception e) {
            System.out.println("Could not complete request. Error: " + e.getMessage());
        }
        return numOrders;
    }


    @Override
    public Order findOrderById(int orderId) {
        Order order = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setDefaultReadOnly(true);
            order = session.get(Order.class, orderId);
        } catch (Exception e) {
            System.err.println("Error while finding order with ID " + orderId + ": " + e.getMessage());
        }
        return order;
    }

}

// Previous implementation of addOrder before using Hibernate.
//    public void addOrder(String orderDetails) {
//        String sql = "INSERT INTO orders (order_details) VALUES (?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, orderDetails);
//            int affectedRows = pstmt.executeUpdate();
//
//            if (affectedRows == 1) {
//
//                System.out.println("Order added successfully");
//            } else {
//                System.out.println("Order was not added");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error adding order: " + e.getMessage());
//        }
//    }