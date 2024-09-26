import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Directions {
    String direction;
    int steps;

    Directions(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите задачу: ");
            System.out.println("1 - Сиркузская последовательность");
            System.out.println("2 - Знакочередующаяся сумма");
            System.out.println("3 - Поиск клада");
            System.out.println("4 - Дороги и туннели");
            System.out.println("5 - Дважды четное число");
            System.out.println("0 - Выход");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    syracuseSequence(scanner);
                    break;
                case 2:
                    alternatingSum(scanner);
                    break;
                case 3:
                    treasureHunt(scanner);
                    break;
                case 4:
                    roadAndTunnels(scanner);
                    break;
                case 5:
                    doubleEvenNumber(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private static void syracuseSequence(Scanner scanner) {
        System.out.print("Введите натуральное число: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Пожалуйста, введите натуральное число.");
            return;
        }
        int steps = 0;
        System.out.print("Сиркузская последовательность: " + n);
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            System.out.print(" -> " + n);
            steps++;
        }
        System.out.println("\nКоличество шагов до достижения 1: " + steps);
    }

    private static void alternatingSum(Scanner scanner) {
        System.out.print("Введите натуральное число n > 0: ");
        int n = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("Введите число " + i + ": ");
            int number = scanner.nextInt();
            if (i % 2 != 0) {
                sum += number;
            } else {
                sum -= number;
            }
        }
        System.out.println("Знакочередующаяся сумма: " + sum);
    }

    private static void treasureHunt(Scanner scanner) {
        System.out.print("Введите координаты клада (x y): ");
        int treasureX = scanner.nextInt();
        int treasureY = scanner.nextInt();
        scanner.nextLine();
        List<Directions> directionsList = new ArrayList<>();
        while (true) {
            System.out.print("Введите направление и количество шагов (или \"стоп\" для завершения): ");
            String direction = scanner.nextLine();
            if (direction.equalsIgnoreCase("стоп")) {
                break;
            }
            int steps = scanner.nextInt();
            scanner.nextLine();
            directionsList.add(new Directions(direction, steps));
        }
        int minSteps = calculateMinSteps(directionsList, treasureX, treasureY);
        System.out.println("Минимальное количество указаний для достижения клада: " + minSteps);
    }

    private static int calculateMinSteps(List<Directions> directionsList, int targetX, int targetY) {
        int currentX = 0;
        int currentY = 0;
        int minSteps = 0;
        for (Directions dir : directionsList) {
            switch (dir.direction) {
                case "север":
                    currentY += dir.steps;
                    break;
                case "юг":
                    currentY -= dir.steps;
                    break;
                case "восток":
                    currentX += dir.steps;
                    break;
                case "запад":
                    currentX -= dir.steps;
                    break;
            }
            minSteps++;
            if (currentX == targetX && currentY == targetY) {
                break;
            }
        }
        return minSteps;
    }

    private static void roadAndTunnels(Scanner scanner) {
        System.out.print("Введите количество дорог: ");
        int numberOfRoads = scanner.nextInt();
        int maxHeight = 0;
        int bestRoad = -1;
        for (int i = 1; i <= numberOfRoads; i++) {
            System.out.print("Введите количество туннелей для дороги " + i + ": ");
            int numberOfTunnels = scanner.nextInt();
            int minHeightForRoad = Integer.MAX_VALUE;
            for (int j = 0; j < numberOfTunnels; j++) {
                System.out.print("Введите высоту туннеля " + (j + 1) + ": ");
                int tunnelHeight = scanner.nextInt();
                if (tunnelHeight < minHeightForRoad) {
                    minHeightForRoad = tunnelHeight;
                }
            }
            if (minHeightForRoad > maxHeight) {
                maxHeight = minHeightForRoad;
                bestRoad = i;
            }
        }
        System.out.println(bestRoad + " " + maxHeight);
    }

    private static void doubleEvenNumber(Scanner scanner) {
        System.out.print("Введите положительное трехзначное число: ");
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number >= 100 && number <= 999) {
                if (isDoubleEven(number)) {
                    System.out.println("Число " + number + " является 'дважды четным'.");
                } else {
                    System.out.println("Число " + number + " не является 'дважды четным'.");
                }
            } else {
                System.out.println("Ошибка: Введите трехзначное число.");
            }
        } else {
            System.out.println("Ошибка: Введите целое число.");
        }
    }

    private static boolean isDoubleEven(int number) {
        int sum = 0;
        int product = 1;
        while (number > 0) {
            int digit = number % 10;
            sum += digit;
            product *= digit;
            number /= 10;
        }
        return (sum % 2 == 0) && (product % 2 == 0);
    }
}
