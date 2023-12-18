package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final String ADD = "a";
    private static final String MARK_COMPLETE = "m";
    private static final String VIEW = "v";
    private static final String QUIT = "q";
    private final List<Order> orderList = new ArrayList<>();


    public Application() {}

    public  void run() {
        System.out.println("Welcome to the Arima Online Ordering System.\n");
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals(QUIT)) {
            System.out.printf("""
                    Please selection an action:
                        Add Order (%s)
                        View All Orders (%s)
                        Mark an Order as Completed (%s)
                        Quit (%s)""
                    %n""", ADD, VIEW, MARK_COMPLETE, QUIT);
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            switch (userInput) {
                case ADD -> handleAddOrder(scanner);
                case VIEW -> handleViewAllOrders();
                case MARK_COMPLETE -> handleMarkOrderCompleted(scanner);
                case QUIT -> System.out.println("Exiting application...");
                default -> System.out.println("You have not entered a valid input.\n");
            }
        }
        scanner.close();
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

    private  Order findOrderById(int orderId) {
        Order order = null;
        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderId() == orderId) {
                order = currentOrder;
                break;
            }
        }
        return order;
    }

    private  void handleAddOrder(Scanner scanner) {
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
}

