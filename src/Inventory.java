import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<StockTransaction> transactions = new ArrayList<>();
    private int nextSequenceNo = 1;

    // Returns false for null or a duplicate ID (case-insensitive)
    public boolean addProduct(Product p) {
        if (p == null || findById(p.getId()) != null) {
            return false;
        }
        products.add(p);
        return true;
    }

    // Case-insensitive lookup; returns null if not found
    public Product findById(String id) {
        if (id == null) {
            return null;
        }
        String wanted = id.trim();
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(wanted)) {
                return p;
            }
        }
        return null;
    }

    // Case-insensitive "contains" search on the name
    public ArrayList<Product> searchByName(String keyword) {
        ArrayList<Product> results = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        String needle = keyword.trim().toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(needle)) {
                results.add(p);
            }
        }
        return results;
    }

    // The update methods return false if the product is not found.
    // Invalid values make Product throw IllegalArgumentException with a clear
    // message, which the menu code can catch and show to the user.
    public boolean updateName(String id, String name) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setName(name);
        return true;
    }

    public boolean updatePrice(String id, double price) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setUnitPrice(price);
        return true;
    }

    public boolean updateReorderLevel(String id, int level) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setReorderLevel(level);
        return true;
    }

    public boolean updateCategoryDetail(String id, String value) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.updateCategoryDetail(value);
        return true;
    }

    // Transaction history is kept even after a product is deleted
    public boolean deleteProduct(String id) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        products.remove(p);
        return true;
    }

    public boolean stockIn(String id, int qty) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.addStock(qty);
        transactions.add(new StockTransaction(nextSequenceNo++, "IN", p.getId(), qty));
        return true;
    }

    public boolean stockOut(String id, int qty) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.removeStock(qty);
        transactions.add(new StockTransaction(nextSequenceNo++, "OUT", p.getId(), qty));
        return true;
    }

    public boolean isLowStock(String id) {
        Product p = findById(id);
        return p != null && p.isLowStock();
    }

    public ArrayList<Product> getProducts() { return new ArrayList<>(products); }

    public ArrayList<StockTransaction> getTransactions() { return new ArrayList<>(transactions); }

    // Bakery prices are from the team's list. Quantities, reorder levels, shelf
    // lives, and the whole beverage list are placeholders to replace.
    public void loadSampleData() {
        addProduct(new BakedGood("BG001", "Cookies", 90, 40, 10, 5));
        addProduct(new BakedGood("BG002", "Chocolate Muffins", 75, 30, 10, 3));
        addProduct(new BakedGood("BG003", "Cinnamon Rolls", 80, 25, 8, 2));
        addProduct(new BakedGood("BG004", "Croissants", 95, 20, 8, 2));

        addProduct(new Beverage("BV001", "Brewed Coffee", 60, 50, 15, 240));
        addProduct(new Beverage("BV002", "Iced Tea", 55, 45, 12, 350));
        addProduct(new Beverage("BV003", "Hot Chocolate", 85, 35, 10, 300));
        addProduct(new Beverage("BV004", "Mango Smoothie", 110, 30, 8, 450));
        addProduct(new Beverage("BV005", "Iced Latte", 95, 40, 11, 400));
        addProduct(new Beverage("BV006", "Orange Juice", 70, 28, 9, 330));
    }
}