package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_details")
    private String orderDetails;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    // Constructor for Hibernate
    protected Order() {
    }

    // Constructor when using the in-memory order service
    public Order(String orderDetails) {
        this.orderDetails = orderDetails;
        this.creationDateTime = LocalDateTime.now();
        this.isCompleted = false;
    }

    // Constructor used when creating Order objects from database data
    public Order(int orderId, String orderDetails, boolean isCompleted, LocalDateTime creationDateTime) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.isCompleted = isCompleted;
        this.creationDateTime = creationDateTime;
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
