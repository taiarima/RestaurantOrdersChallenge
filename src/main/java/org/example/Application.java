package org.example;

import java.util.Scanner;
import static org.example.UIConstants.*;

public class Application {

    private final OrderService orderService;

    public Application(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run(Scanner scanner) {
        System.out.println("Welcome to the Arima Online Ordering System.\n");
        String userInput = "";
        while (!userInput.equals(QUIT)) {
            System.out.printf("""
                    Please selection an action:
                        Add Order (%s)
                        View All Orders (%s)
                        Mark an Order as Completed (%s)
                        Edit order details (%s)
                        Delete an Order (%s)
                        Quit (%s)
                    %n""", ADD, VIEW, MARK_COMPLETE, EDIT, DELETE, QUIT);
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            switch (userInput) {
                case ADD -> handleAddOrder(scanner);
                case VIEW -> handleViewAllOrders();
                case MARK_COMPLETE -> handleMarkOrderCompleted(scanner);
                case EDIT -> handleEditDetails(scanner);
                case DELETE -> handleDeleteOrder(scanner);
                case QUIT -> System.out.println("Exiting application...");
                default -> System.out.println("You have not entered a valid input.\n");
            }
        }
        scanner.close();
    }

    private void handleEditDetails(Scanner scanner) {
        System.out.println("Please enter the order ID of the order you would like to edit.");
        Integer orderId = confirmValidId(scanner);
        if (orderId == null) {
            System.out.println("You have not entered a valid order ID. Returning to main menu.\n");
        } else {
            Order order = orderService.findOrderById(orderId);
            System.out.printf("""
                    The current order details for order %d are:
                    %s
                    
                    Please enter the new details you would like save for this order:
                    """, orderId, order.getOrderDetails());
            String newDetails = scanner.nextLine();
            String message = """
                    The current order's details will be replaced with the following:
                    %s
                    
                    Press y to confirm, n to abort action and return to main menu.
                    """.formatted(newDetails);
            if (confirmAction(scanner, message)) {
                orderService.editOrderDetails(order, newDetails);
                System.out.println("Order details successfully updated.");
            } else {
                System.out.println(ACTION_ABORTED);
            }
        }
    }

    private void handleDeleteOrder(Scanner scanner) {
        Integer orderId;
        do {
            System.out.println("Type the order ID of the order you would like to delete and press enter.");
            orderId = confirmValidId(scanner);
            if (orderId != null) {
                String message = """
                        You have entered order id %d
                                        
                        Are you sure would like to delete the order?
                        This action is permanent and cannot be undone!
                        Press y to confirm, press n to abort action and return to main menu.
                                        
                        %n""".formatted(orderId);
                if (confirmAction(scanner, message)) {
                    orderService.deleteOrder(orderId);
                    System.out.printf("Order #%d successfully deleted\n", orderId);
                } else {
                    System.out.println(ACTION_ABORTED);
                    break;
                }
            }
        } while (orderId == null);
    }

    private Integer confirmValidId(Scanner scanner) {
        Integer orderId = null;
        String inputLine = scanner.nextLine();
        try {
            orderId = Integer.parseInt(inputLine);
            Order order = orderService.findOrderById(orderId);
            if (order == null) {
                System.out.println("You have not entered a valid order ID.");
                orderId = null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid order ID as an integer.");
        }
        return orderId;
    }

    private boolean confirmAction(Scanner scanner, String message) {
        boolean isConfirmed;
        System.out.printf(message);
        String confirmInput = scanner.nextLine();
        confirmInput = confirmInput.toLowerCase();
        if (confirmInput.equals("y")) {
            isConfirmed = true;
        } else if (confirmInput.equals("n")) {
            isConfirmed = false;
        } else {
            System.out.println("You have not entered a valid input. Returning to main menu.\n");
            isConfirmed = false;
        }
        return isConfirmed;
    }

    private void handleViewAllOrders() {
        System.out.println("Displaying all orders: \n");
        orderService.viewAllOrders();
        System.out.printf("""
                        
                Process finished. %d orders found.
                        
                """, orderService.getNumberOrders());
    }

    private void handleAddOrder(Scanner scanner) {
        String detailsCorrect = "n";
        String orderDetails = "";
        while (!detailsCorrect.equals("y")) {
            System.out.println("Type the details of your order and press enter.\n ");
            orderDetails = scanner.nextLine();
            // TODO: Implement confirmAction method here
            System.out.printf("""
                    You have entered the order details as: %s
                                
                    Are these details correct? (y/n)
                    %n""", orderDetails);
            detailsCorrect = scanner.nextLine();
            detailsCorrect = detailsCorrect.toLowerCase();
            if (!detailsCorrect.equals("y") && !detailsCorrect.equals("n")) {
                System.out.println("You have not entered a valid input.");
            }
        }
        orderService.addOrder(orderDetails);
    }

    private  void handleMarkOrderCompleted(Scanner scanner) {
        Integer orderId = confirmValidId(scanner);
        if (orderId == null) {
            System.out.println("You have not entered a valid ID. Returning to main menu...");
            return;
        }
        String message = """
                        You have entered order id %d
                                        
                        Would you like to proceed to mark this order as completed? (y/n)
                                        
                        %n""".formatted(orderId);
        boolean isConfirmed = confirmAction(scanner, message);
        if (isConfirmed) {
            orderService.markOrderCompleted(orderId);
            System.out.printf("""
                    Operation successful. Order #%d marked as completed.
                    """, orderId);
        } else {
            System.out.println("Operation aborted. Returning to main menu...");
        }
    }
}

