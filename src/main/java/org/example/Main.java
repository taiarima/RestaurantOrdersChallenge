package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application application = null;

        while (true) {
            System.out.println("Would you like to use the In-Memory version (1) or the Database version (2) of the application? (Enter your choice as 1 or 2 or Q to quit, and hit enter): ");
            String versionSelect = scanner.nextLine();

            switch (versionSelect.toLowerCase()) {
                case "1":
                    application = new Application(new InMemoryOrderService());
                    break;
                case "2":
                    application = new Application(new DatabaseOrderService());
                    break;
                case "q":
                    System.out.println("You have chosen to quit. Now exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    continue;
            }
            break;
        }

        if (application != null) {
            application.run(scanner);
        }

        scanner.close();
    }
}
