import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    // Тест 1: проверка стоимости Courier
    @Test
    public void testCourierCost() {
        Delivery delivery = new Courier("Документы", "C001", 1200L);

        assertEquals(1200L, delivery.cost());
    }

    // Тест 2: проверка стоимости Post
    @Test
    public void testPostCost() {
        Delivery delivery = new Post("Посылка", "P002", 450L);

        assertEquals(450L, delivery.cost());
    }

    // Тест 3: проверка стоимости Drone
    @Test
    public void testDroneCost() {
        Delivery delivery = new Drone("Срочный заказ", "D003", 2500L);

        assertEquals(2500L, delivery.cost());
    }

    // Тест 4: проверка общей стоимости через Calculation
    @Test
    public void testTotalCalculation() {
        Delivery[] deliveries = {
                new Courier("Документы", "C001", 1200L),
                new Post("Посылка", "P002", 450L),
                new Drone("Заказ", "D003", 2500L)
        };

        DeliveryService service = new DeliveryService(deliveries);

        Calculation calculation = delivery -> delivery.cost();

        double actual = service.calculateTotal(calculation);

        assertEquals(4150.0, actual);
    }

    // Тест 5: проверка отбора доставок по порогу
    @Test
    public void testSelection() {
        Delivery[] deliveries = {
                new Courier("Документы", "C001", 1200L),
                new Post("Посылка", "P002", 450L),
                new Drone("Заказ", "D003", 2500L)
        };

        long threshold = 1000L;
        Selection selection = delivery -> delivery.cost() > threshold;

        assertTrue(selection.test(deliveries[0]));
        assertFalse(selection.test(deliveries[1]));
        assertTrue(selection.test(deliveries[2]));
    }
}
