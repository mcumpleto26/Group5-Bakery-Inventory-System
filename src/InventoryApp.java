import java.util.Scanner;

// Menu skeleton and console input validation helpers for Member 3
public class InventoryApp {
    private Scanner scanner = new Scanner(System.in);
    private Inventory inventory = new Inventory();

    public static void main(String[] args) {
        InventoryApp app = new InventoryApp();
        app.run();
    }

    private void run() {
        inventory.loadSampleData();
        int choice;
        do {
            printMenu();
            choice = readInt("Choose an option: ");
            switch (choice) {
                case 1: System.out.println("Add product: coming soon"); break;
                case 2: System.out.println("View all: coming soon"); break;
                case 3: System.out.println("Search: coming soon"); break;
                case 4: System.out.println("Update: coming soon"); break;
                case 5: System.out.println("Delete: coming soon"); break;
                case 6: System.out.println("Stock in: coming soon"); break;
                case 7: System.out.println("Stock out: coming soon"); break;
                case 8: System.out.println("Low stock: coming soon"); break;
                case 9: System.out.println("Sort: coming soon"); break;
                case 10: System.out.println("Summary: coming soon"); break;
                case 11: System.out.println("History: coming soon"); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Choose a number from 0 to 11."); break;
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== BAKERY & CAFE INVENTORY =====");
        System.out.println("1. Add product");
        System.out.println("2. View all products");
        System.out.println("3. Search product");
        System.out.println("4. Update product");
        System.out.println("5. Delete product");
        System.out.println("6. Stock in");
        System.out.println("7. Stock out");
        System.out.println("8. Low-stock alerts");
        System.out.println("9. Sort products");
        System.out.println("10. Summary report");
        System.out.println("11. Transaction history");
        System.out.println("0. Exit");
    }

    // Reads a whole number safely using nextLine() to avoid scanner skip issues
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    // Reads a decimal number safely
    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    // Ensures non-empty trimmed text input
    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    // Reads Y or N confirmation case-insensitively
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N.");
        }
    }
}
