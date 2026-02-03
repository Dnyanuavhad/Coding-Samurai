import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("ID: %s | %-15s | $%.2f", id, name, price);
    }
}

public class ECommerce {
    private static ArrayList<Product> catalog = new ArrayList<>();
    private static ArrayList<Product> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isLoggedIn = false;

    public static void main(String[] args) {
        initializeCatalog();
        
        System.out.println("Welcome to the E-Shop!");
        if (handleLogin()) {
            showMenu();
        } else {
            System.out.println("Login failed. Exiting.");
        }
    }

    private static void initializeCatalog() {
        catalog.add(new Product("P001", "Laptop", 90.000));
        catalog.add(new Product("P002", "Smartphone", 20.000));
        catalog.add(new Product("P003", "Headphones", 5.000));
    }

    private static boolean handleLogin() {
        System.out.print("Enter Username: ");
        String user = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();
        
        if (user.equals("admin") && pass.equals("1234")) {
            isLoggedIn = true;
            System.out.println("Login Successful!\n");
            return true;
        }
        return false;
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Browse Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart & Checkout");
            System.out.println("4. Exit");
            System.out.print("Selection: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> browseProducts();
                case 2 -> addToCart();
                case 3 -> checkout();
                case 4 -> {
                    System.out.println("Thank you for shopping!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void browseProducts() {
        System.out.println("\n--- AVAILABLE PRODUCTS ---");
        for (Product p : catalog) System.out.println(p);
    }

    private static void addToCart() {
        System.out.print("Enter Product ID to add: ");
        String id = scanner.nextLine();
        for (Product p : catalog) {
            if (p.getId().equalsIgnoreCase(id)) {
                cart.add(p);
                System.out.println(p.getName() + " added to cart.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double total = 0;
        System.out.println("\n--- YOUR CART ---");
        for (Product p : cart) {
            System.out.println("- " + p.getName() + ": $" + p.getPrice());
            total += p.getPrice();
        }
        System.out.printf("TOTAL DUE: $%.2f\n", total);
        System.out.print("Confirm Purchase? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            System.out.println("Payment Successful! Your items are on the way.");
            cart.clear();
        }
    }
}