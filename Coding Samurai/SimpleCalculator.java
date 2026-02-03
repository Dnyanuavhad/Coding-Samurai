
import java.util.Scanner;

public class SimpleCalculator {

    static double add(double a, double b) {
        return a + b;
    }

    static double subtract(double a, double b) {
        return a - b;
    }

    static double multiply(double a, double b) {
        return a * b;
    }

    static double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
        return a / b;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
        boolean run = true;

        while (run) {
            System.out.println("\n****** Calculator Menu *****");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Choose an operation: ");

            choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Thank you for using Calculator!");
                break;
            }

            System.out.print("Enter first number: ");
            double num1 = sc.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = sc.nextDouble();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Addition is = " + add(num1, num2));
                        break;

                    case 2:
                        System.out.println("Substraction is = " + subtract(num1, num2));
                        break;

                    case 3:
                        System.out.println("Multiplication is = " + multiply(num1, num2));
                        break;

                    case 4:
                        System.out.println("Division is = " + divide(num1, num2));
                        break;

                    default:
                        System.out.println("Invalid choice! Please select 1 to 5.");
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}

