import java.util.ArrayList;
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
                case 1: addProduct(); break;
                case 2: viewAllProducts(); break;
                case 3: searchProduct(); break;
                case 4: updateProduct(); break;
                case 5: deleteProduct(); break;
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
    private void printHeader() {
        System.out.printf("%-8s %-20s %-12s %10s %6s %s%n",
                "ID", "Name", "Category", "Price", "Qty", "Details");
        System.out.println("------------------------------------------------------------------------");
    }

    private void printRow(Product p) {
        System.out.printf("%-8s %-20s %-12s %10.2f %6d %s%n",
                p.getId(), p.getName(), p.getCategory(),
                p.getUnitPrice(), p.getQuantity(), p.getCategoryDetails());
    }

    // Menu option 2: shows every product in a table
    private void viewAllProducts() {
        ArrayList<Product> items = inventory.getProducts();
        if (items.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        printHeader();
        for (Product p : items) {
            printRow(p);
        }
    }

    // Menu option 1: adds a new baked good, beverage, or ingredient
    private void addProduct() {
        System.out.println("--- Add product ---");
        System.out.println("1. Baked Good");
        System.out.println("2. Beverage");
        System.out.println("3. Ingredient");
        int type;
        while (true) {
            type = readInt("Choose a category (1-3): ");
            if (type >= 1 && type <= 3) {
                break;
            }
            System.out.println("Please choose 1, 2, or 3.");
        }

        String id = readNonEmpty("Enter ID: ");
        if (inventory.findById(id) != null) {
            System.out.println("A product with ID " + id + " already exists.");
            return;
        }
        String name = readNonEmpty("Enter name: ");
        double price = readDouble("Enter unit price: ");
        int quantity = readInt("Enter starting quantity: ");
        int reorderLevel = readInt("Enter reorder level: ");

        try {
            Product p;
            if (type == 1) {
                int shelfLife = readInt("Enter shelf life in days: ");
                p = new BakedGood(id, name, price, quantity, reorderLevel, shelfLife);
            } else if (type == 2) {
                int sizeMl = readInt("Enter size in ml: ");
                p = new Beverage(id, name, price, quantity, reorderLevel, sizeMl);
            } else {
                String unit = readNonEmpty("Enter unit (for example kg or L): ");
                p = new Ingredient(id, name, price, quantity, reorderLevel, unit);
            }

            if (inventory.addProduct(p)) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Could not add the product.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add product: " + e.getMessage());
        }
    }

    // Menu option 3: finds products whose name contains the keyword
    private void searchProduct() {
        String keyword = readNonEmpty("Enter part of the product name: ");
        ArrayList<Product> results = inventory.searchByName(keyword);
        if (results.isEmpty()) {
            System.out.println("No products found for \"" + keyword + "\".");
            return;
        }
        System.out.println(results.size() + " product(s) found:");
        printHeader();
        for (Product p : results) {
            printRow(p);
        }
    }

    // Menu option 4: changes one field of an existing product
    private void updateProduct() {
        String id = readNonEmpty("Enter the ID of the product to update: ");
        Product p = inventory.findById(id);
        if (p == null) {
            System.out.println("No product found with ID " + id + ".");
            return;
        }

        printHeader();
        printRow(p);
        System.out.println();
        System.out.println("1. Name");
        System.out.println("2. Unit price");
        System.out.println("3. Reorder level");
        System.out.println("4. " + p.getCategory() + " detail");
        System.out.println("0. Cancel");
        int field = readInt("What do you want to update? ");

        try {
            switch (field) {
                case 1:
                    inventory.updateName(id, readNonEmpty("Enter new name: "));
                    break;
                case 2:
                    inventory.updatePrice(id, readDouble("Enter new unit price: "));
                    break;
                case 3:
                    inventory.updateReorderLevel(id, readInt("Enter new reorder level: "));
                    break;
                case 4:
                    inventory.updateCategoryDetail(id, readNonEmpty(p.getDetailPrompt()));
                    break;
                case 0:
                    System.out.println("Update cancelled.");
                    return;
                default:
                    System.out.println("Choose a number from 0 to 4.");
                    return;
            }
            System.out.println("Product updated successfully.");
            printHeader();
            printRow(p);
        } catch (NumberFormatException e) {
            System.out.println("Update failed: please enter a valid whole number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // Menu option 5: removes a product after confirmation
    private void deleteProduct() {
        String id = readNonEmpty("Enter the ID of the product to delete: ");
        Product p = inventory.findById(id);
        if (p == null) {
            System.out.println("No product found with ID " + id + ".");
            return;
        }

        printHeader();
        printRow(p);
        if (readYesNo("Delete this product? (Y/N): ")) {
            inventory.deleteProduct(id);
            System.out.println("Product deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }
}