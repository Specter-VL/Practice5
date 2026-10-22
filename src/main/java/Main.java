public class Main {

    public static void main(String[] args) {
        Delivery[] deliveries = {
                new Courier("Документы", "C001", 1200L),
                new Post("Посылка", "P002", 450L),
                new Drone("Срочный заказ", "D003", 2500L),
                new Courier("Коробка", "C004", 1800L),
                new Post("Письмо", "P005", 200L)
        };

        DeliveryService service = new DeliveryService(deliveries);

        // 1. Calculation: анонимный класс
        Calculation anonymousCalculation = new Calculation() {
            @Override
            public double calculate(Delivery delivery) {
                return delivery.cost();
            }
        };

        double anonymousTotal =
                service.calculateTotal(anonymousCalculation);

        // 2. Calculation: лямбда
        Calculation lambdaCalculation =
                delivery -> delivery.cost();

        double lambdaTotal =
                service.calculateTotal(lambdaCalculation);

        System.out.println("=== Сравнение расчетов ===");
        System.out.println("Анонимный класс: " + anonymousTotal);
        System.out.println("Лямбда: " + lambdaTotal);
        System.out.println(
                "Суммы совпадают: "
                        + (anonymousTotal == lambdaTotal)
        );

        // 3. Selection: первый порог
        long firstThreshold = 1000L;

        Selection firstSelection =
                delivery -> delivery.cost() > firstThreshold;

        System.out.println(
                "\n=== Доставки дороже " + firstThreshold + " ==="
        );
        service.printSelected(firstSelection);

        // 4. Selection: второй порог
        long secondThreshold = 2000L;

        Selection secondSelection =
                delivery -> delivery.cost() > secondThreshold;

        System.out.println(
                "\n=== Доставки дороже " + secondThreshold + " ==="
        );
        service.printSelected(secondSelection);

        // 5. Runnable: ссылка на метод
        Runnable report = service::printReport;
        report.run();
    }
}