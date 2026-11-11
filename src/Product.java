public abstract class Product {
    private String id;
    private String name;
    private double unitPrice;
    private int quantity;
    private int reorderLevel;

     public Product(String id, String name, double unitPrice, int quantity, 
int reorderLevel) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id.trim();
        setName(name);
        setUnitPrice(unitPrice);
        setQuantity(quantity);
        setReorderLevel(reorderLevel);
    }

     public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }
    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

      this.unitPrice = unitPrice;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public void setReorderLevel(int reorderLevel) {
        if (reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }
        this.reorderLevel = reorderLevel;
    }

     public void addStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        quantity += amount;
    }

     public void removeStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock. Available: " + quantity);
        }
        quantity -= amount;
    }

     public double getInventoryValue() {
        return unitPrice * quantity;
    }
    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

     // getId(), getName(), getUnitPrice(), getQuantity(), getReorderLevel() 

    public abstract String getCategory();
    public abstract String getCategoryDetails();
    public abstract String getDetailPrompt();
    public abstract void updateCategoryDetail(String value);
}

