package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Application application = new Application();
        Scanner scanner = new Scanner(System.in);
        application.run(scanner);
    }
}