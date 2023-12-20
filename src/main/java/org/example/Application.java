package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static final String ADD = "a";
    public static final String MARK_COMPLETE = "m";
    public static final String VIEW = "v";
    public static final String QUIT = "q";
    public static final String EDIT = "e";
    public static final String DELETE = "d";
    private final List<Order> orderList = new ArrayList<>();


    public Application() {}

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
                        Quit (%s)""
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
        // Show current details of that order
        // Ask for new ones
        // Confirm
        // Execute
        Integer orderId = confirmValidId(scanner);
        if (orderId == null) {
            System.out.println("You have not entered a valid order ID. Returning to main menu.\n");
        } else {
            Order order = findOrderById(orderId);
            System.out.printf("""
                    The current order details for order %d are:
                    %s
                    
                    Please enter the new details you would like save for this order:
                    """, orderId, order.getOrderDetails());
        }
    }

    private void handleDeleteOrder(Scanner scanner) {
        Integer orderId = null;
        do {
            System.out.println("Type the order ID of the order you would like to delete and press enter.");
            orderId = confirmValidId(scanner);
            if (orderId != null) {
                String message = """
                        You have entered order id %d
                                        
                        Would you like to proceed to mark this order as completed? (y/n)
                                        
                        %n""".formatted(orderId);
                if (confirmAction(scanner, message)) {
                    deleteOrder(orderId);
                } else {
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
            if (orderId < 0 || orderId >= Order.getIncrementingCounter()) {
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
            isConfirmed =false;
        } else {
            System.out.println("You have not entered a valid input. Returning to main menu.\n");
            isConfirmed = false;
        }
        return isConfirmed;
    }

    private  void addOrder(String orderDetails) {
        Order newOrder = new Order(orderDetails);
        orderList.add(newOrder);
        System.out.printf("""
                You successfully added the following order:
                %s
                %n""", newOrder);
    }

    private  void handleViewAllOrders() {
        System.out.println("Displaying all orders: \n");
        orderList.forEach((order)->System.out.print(order.toString()));
        System.out.printf("""
                        
                Process finished. %d orders found.
                        
                %n""", orderList.size());
    }

    private  void markOrderCompleted(int orderId) {
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

    private Order findOrderById(int orderId) {
        Order order = null;
        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderId() == orderId) {
                order = currentOrder;
                break;
            }
        }
        return order;
    }

    private void deleteOrder(int orderId) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId() == orderId) {
                orderList.remove(i);
                break;
            }
        }
    }

    private void editOrderDetails(Order order, String newDetails) {
        order.setOrderDetails(newDetails);
    }

    private void handleAddOrder(Scanner scanner) {
        String detailsCorrect = "n";
        String orderDetails = "";
        while (!detailsCorrect.equals("y")) {
            System.out.println("Type the details of your order and press enter.\n ");
            orderDetails = scanner.nextLine();
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
        addOrder(orderDetails);
    }

    private  void handleMarkOrderCompleted(Scanner scanner) {
        Integer orderId = null;
        while (orderId == null) {
            System.out.println("Enter the order ID of the order you would like to mark as completed.\n");
            String inputLine = scanner.nextLine();
            try {
                orderId = Integer.parseInt(inputLine);
                if (orderId < 0 || orderId >= Order.getIncrementingCounter()) {
                    System.out.println("You have not entered a valid order ID.");
                    orderId = null;
                    continue;
                }
                System.out.printf("""
                        You have entered order id %d
                                        
                        Would you like to proceed to mark this order as completed? (y/n)
                                        
                        %n""", orderId);
                String confirmInput = scanner.nextLine();
                confirmInput = confirmInput.toLowerCase();
                if (confirmInput.equals("y")) {
                    markOrderCompleted(orderId);
                    break;
                } else if (confirmInput.equals("n")) {
                    orderId = null;
                } else {
                    System.out.println("You have not entered a valid input. Returning to main menu.\n");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid order ID as an integer.");
            }
        }
    }

    public List<Order> getOrderList() {
        return this.orderList;
    }
}


