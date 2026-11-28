import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<StockTransaction> transactions = new ArrayList<>();
    private int nextSequenceNo = 1;

    // Saves one stock movement or change in the history
    private void recordTransaction(String type, String productId, int qty) {
        transactions.add(new StockTransaction(nextSequenceNo++, type, productId, qty));
    }

    // Recursive search: returns the index of the product, or -1 if not found
    private int findIndexById(String id, int index) {
        if (index >= products.size()) {
            return -1;                          // base case: not found
        }
        if (products.get(index).getId().equalsIgnoreCase(id)) {
            return index;                       // base case: found
        }
        return findIndexById(id, index + 1);    // recursive case: check the next one
    }

    // Case-insensitive lookup; returns null if not found
    public Product findById(String id) {
        if (id == null) {
            return null;
        }
        int index = findIndexById(id.trim(), 0);
        return (index == -1) ? null : products.get(index);
    }

    // Returns false for null or a duplicate ID
    public boolean addProduct(Product p) {
        if (p == null || findById(p.getId()) != null) {
            return false;
        }
        products.add(p);
        recordTransaction("ADD", p.getId(), p.getQuantity());
        return true;
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
    // Invalid values make Product throw IllegalArgumentException, which the menu catches.
    public boolean updateName(String id, String name) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setName(name);
        recordTransaction("UPDATE", p.getId(), 0);
        return true;
    }

    public boolean updatePrice(String id, double price) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setUnitPrice(price);
        recordTransaction("UPDATE", p.getId(), 0);
        return true;
    }

    public boolean updateReorderLevel(String id, int level) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.setReorderLevel(level);
        recordTransaction("UPDATE", p.getId(), 0);
        return true;
    }

    // Polymorphic call: Inventory never needs to know the category
    public boolean updateCategoryDetail(String id, String value) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.updateCategoryDetail(value);
        recordTransaction("UPDATE", p.getId(), 0);
        return true;
    }

    // The history keeps the DELETE record after the product is gone
    public boolean deleteProduct(String id) {
        if (id == null) {
            return false;
        }
        int index = findIndexById(id.trim(), 0);
        if (index == -1) {
            return false;
        }
        Product p = products.get(index);
        int leftInStock = p.getQuantity();
        products.remove(index);
        recordTransaction("DELETE", p.getId(), leftInStock);
        return true;
    }

    public boolean stockIn(String id, int qty) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.addStock(qty);
        recordTransaction("STOCK_IN", p.getId(), qty);
        return true;
    }

    public boolean stockOut(String id, int qty) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        p.removeStock(qty);   // throws IllegalArgumentException if qty is invalid or too large
        recordTransaction("STOCK_OUT", p.getId(), qty);
        return true;
    }

    public boolean isLowStock(String id) {
        Product p = findById(id);
        return p != null && p.isLowStock();
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public ArrayList<StockTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

       // Team products plus ingredients. BG004, BV005, and IN002 start at or below
    // their reorder level on purpose, so the low-stock alert has something to show.
    public void loadSampleData() {
        addProduct(new BakedGood("BG001", "Cookies", 90, 40, 10, 5));
        addProduct(new BakedGood("BG002", "Chocolate Muffins", 75, 30, 10, 3));
        addProduct(new BakedGood("BG003", "Cinnamon Rolls", 80, 25, 8, 2));
        addProduct(new BakedGood("BG004", "Croissants", 95, 6, 8, 2));

        addProduct(new Beverage("BV001", "Brewed Coffee", 60, 50, 15, 240));
        addProduct(new Beverage("BV002", "Iced Tea", 55, 45, 12, 350));
        addProduct(new Beverage("BV003", "Hot Chocolate", 85, 35, 10, 300));
        addProduct(new Beverage("BV004", "Mango Smoothie", 110, 30, 8, 450));
        addProduct(new Beverage("BV005", "Iced Latte", 95, 9, 11, 400));
        addProduct(new Beverage("BV006", "Orange Juice", 70, 28, 9, 330));

        addProduct(new Ingredient("IN001", "All-Purpose Flour", 48.00, 50, 20, "kg"));
        addProduct(new Ingredient("IN002", "White Sugar", 62.00, 6, 10, "kg"));
        addProduct(new Ingredient("IN003", "Fresh Milk", 95.00, 18, 8, "L"));
    }
}