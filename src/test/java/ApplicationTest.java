import static org.junit.jupiter.api.Assertions.*;

import org.example.Application;
import org.example.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

class ApplicationTest {
    // Arrange, Act, Assert
    private Application app;

    @BeforeEach
    void setUp() {
        app = new Application();
    }

    @Test
    void addOrder_AddsOrderToList() {
        String simulatedInput = app.ADD + "\nTest Order Details\ny\n" + app.QUIT + "\n";
        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner testScanner = new Scanner(simulatedInputStream);
        app.run(testScanner);

        List<Order> orders = app.getOrderList();
        Order lastOrder = orders.get(orders.size() - 1);

        assertEquals(1, orders.size());
        assertEquals("Test Order Details", lastOrder.getOrderDetails());

    }
}
