import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<StockTransaction> transactions = new ArrayList<>();
    private int nextSequenceNo = 1;

    public boolean addProduct(Product p) { return false; }
    public Product findById(String id) { return null; }
    public ArrayList<Product> searchByName(String keyword) { return new ArrayList<>(); }
    public boolean updateName(String id, String name) { return false; }
    public boolean updatePrice(String id, double price) { return false; }
    public boolean updateReorderLevel(String id, int level) { return false; }
    public boolean updateCategoryDetail(String id, String value) { return false; }
    public boolean deleteProduct(String id) { return false; }
    public boolean stockIn(String id, int qty) { return false; }
    public boolean stockOut(String id, int qty) { return false; }
    public boolean isLowStock(String id) { return false; }
    public ArrayList<Product> getProducts() { return new ArrayList<>(products); }
    public ArrayList<StockTransaction> getTransactions() { return new ArrayList<>(transactions); }
    public void loadSampleData() { }
}