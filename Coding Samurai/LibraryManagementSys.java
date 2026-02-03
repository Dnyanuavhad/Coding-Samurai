import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        return String.format("ISBN: %s | Title: %-15s | Author: %-12s | Status: %s", isbn, title, author, status);
    }
}

public class LibraryManagementSys {
    private static ArrayList<Book> inventory = new ArrayList<>();
    private static ArrayList<String> history = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        inventory.add(new Book("Java Basics", "John Smith", "101"));
        inventory.add(new Book("OOP Design", "Jane Doe", "102"));
        inventory.add(new Book("Data Structures", "Alan Turing", "103"));

        while (true) {
            System.out.println("\n--- LIBRARY MANAGEMENT SYSTEM ---");
            System.out.println("1. Search Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View Borrowing History");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> searchBooks();
                case 2 -> borrowBook();
                case 3 -> returnBook();
                case 4 -> viewHistory();
                case 5 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void searchBooks() {
        System.out.print("Enter search keyword (Title/Author): ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : inventory) {
            if (b.getTitle().toLowerCase().contains(query) || b.getAuthor().toLowerCase().contains(query)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No books found matching that search.");
    }

    private static void borrowBook() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ISBN to borrow: ");
        String isbn = scanner.nextLine();

        for (Book b : inventory) {
            if (b.getIsbn().equals(isbn)) {
                if (b.isAvailable()) {
                    b.setAvailable(false);
                    history.add(name + " borrowed '" + b.getTitle() + "'");
                    System.out.println("Success! You have borrowed: " + b.getTitle());
                } else {
                    System.out.println("Error: Book is already checked out.");
                }
                return;
            }
        }
        System.out.println("Error: Book with ISBN " + isbn + " not found.");
    }

    private static void returnBook() {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();

        for (Book b : inventory) {
            if (b.getIsbn().equals(isbn)) {
                if (!b.isAvailable()) {
                    b.setAvailable(true);
                    System.out.println("Success! You have returned: " + b.getTitle());
                } else {
                    System.out.println("Error: This book was already in the library.");
                }
                return;
            }
        }
        System.out.println("Error: Invalid ISBN.");
    }

    private static void viewHistory() {
        System.out.println("\n--- Borrowing History ---");
        if (history.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String record : history) {
                System.out.println("- " + record);
            }
        }
    }
}