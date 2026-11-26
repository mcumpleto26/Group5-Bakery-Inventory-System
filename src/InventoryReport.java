import java.util.ArrayList;
import java.util.HashMap; 

public class InventoryReport {
private ArrayList<Product> products;

public InventoryReport(ArrayList<Product> products) {
this.products = products;
}


public ArrayList<Product> getLowStockProducts() {
ArrayList<Product> low = new ArrayList<>();
for (Product p : products) {
if (p.isLowStock()) {
low.add(p);
}
}
return low;
}

public int getTotalUnits() {
int total = 0;
for (Product p : products) {
total += p.getQuantity();
}
return total;
}

private double computeTotalValue(int index) {
if (index == products.size()) {
return 0;
}
return products.get(index).getInventoryValue() + computeTotalValue(index + 1);
}
public double getTotalValue() {
return computeTotalValue(0);
}

public ArrayList<Product> sortBy(String field, boolean ascending) {
ArrayList<Product> sorted = new ArrayList<>(products);
for (int i = 0; i < sorted.size() - 1; i++) {
int best = i;
for (int j = i + 1; j < sorted.size(); j++) {
int cmp = compare(sorted.get(j), sorted.get(best), field);
if ((ascending && cmp < 0) || (!ascending && cmp > 0)) {
best = j;
}
}
Product temp = sorted.get(i);
sorted.set(i, sorted.get(best));
sorted.set(best, temp);
}
return sorted;
}

private int compare(Product a, Product b, String field) {
if (field.equals("name")) {
return a.getName().compareToIgnoreCase(b.getName());
}
if (field.equals("price")) {
return Double.compare(a.getUnitPrice(), b.getUnitPrice());
}
return Integer.compare(a.getQuantity(), b.getQuantity());
}

public String buildSummary() {
    HashMap<String, Integer> countByCategory = new HashMap<>();
    HashMap<String, Double> valueByCategory = new HashMap<>();
    for (Product p : products) {
        String cat = p.getCategory();
        countByCategory.put(cat, countByCategory.getOrDefault(cat, 0) + 1);
        valueByCategory.put(cat, valueByCategory.getOrDefault(cat, 0.0) + p.getInventoryValue());
    }
    StringBuilder sb = new StringBuilder();
    sb.append("===== SUMMARY REPORT =====\n");
    sb.append("Total products: ").append(products.size()).append("\n");
    sb.append("Total units: ").append(getTotalUnits()).append("\n");
    sb.append(String.format("Total inventory value: PHP %.2f%n", getTotalValue()));
    sb.append("\nBy category:\n");
    for (String cat : countByCategory.keySet()) {
        sb.append(String.format("  %-12s %3d product(s)  PHP %.2f%n",cat, countByCategory.get(cat), valueByCategory.get(cat)));
    }
    sb.append("\nLow-stock products:\n");
    ArrayList<Product> low = getLowStockProducts();
    if (low.isEmpty()) {
        sb.append("  None\n");
    }
    for (Product p : low) {
        sb.append("  " + p.getId() + " " + p.getName() + " (qty " + p.getQuantity() + ", reorder level " + p.getReorderLevel() + ")\n");
    }
    return sb.toString();
}

}