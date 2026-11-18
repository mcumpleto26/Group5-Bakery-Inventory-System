import java.util.ArrayList;

public class InventoryReport {
private ArrayList<Product> products;

public InventoryReport(ArrayList<Product> products) {
this.products = products;
}

public ArrayList<Product> getLowStockProducts() { return new ArrayList<>(); }
public ArrayList<Product> sortBy(String field, boolean ascending) { 
return new ArrayList<>(products); }

public int getTotalUnits() { return 0; }
public double getTotalValue() { return 0; }
public String buildSummary() { return ""; }
}