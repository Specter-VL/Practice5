public class DeliveryService {

    private final Delivery[] deliveries;

    public DeliveryService(Delivery[] deliveries) {
        this.deliveries = deliveries;
    }

    public double calculateTotal(Calculation calculation) {
        double total = 0.0;

        for (Delivery delivery : deliveries) {
            total += calculation.calculate(delivery);
        }

        return total;
    }

    public void printSelected(Selection selection) {
        for (Delivery delivery : deliveries) {
            if (selection.test(delivery)) {
                printDelivery(delivery);
            }
        }
    }

    private void printDelivery(Delivery delivery) {
        Trackable trackable = (Trackable) delivery;
        Billable billable = (Billable) delivery;

        System.out.println(
                delivery.getClass().getSimpleName()
                        + " | " + delivery.getName()
                        + " | Код: " + trackable.getTrackingCode()
                        + " | Стоимость: " + delivery.cost()
                        + " | Оплата: " + billable.payment()
        );
    }

    public void printReport() {
        System.out.println("\n=== Отчет о доставках ===");

        for (Delivery value : deliveries) {
            printDelivery(value);
        }

        Calculation calculation = delivery -> delivery.cost();

        System.out.println(
                "Общая стоимость: " + calculateTotal(calculation)
        );
    }
}