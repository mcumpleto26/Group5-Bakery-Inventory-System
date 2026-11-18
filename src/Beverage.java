public class Beverage extends Product {
    private int sizeMl;
    public Beverage(String id, String name, double unitPrice, int quantity,
                     int reorderLevel, int sizeMl) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setSizeMl(sizeMl);
    }

    public void setSizeMl(int sizeMl) {
        if (sizeMl < 1) {
            throw new IllegalArgumentException("Size must be at least 1 ml.");
        }
        this.sizeMl = sizeMl;
    }

     @Override
    public String getCategory() {
        return "Beverage";
    }
    @Override
    public String getCategoryDetails() {
        return "Size: " + sizeMl + " ml";
    }
    @Override
    public String getDetailPrompt() {
        return "Enter new size in ml: ";
    }
     @Override
public void updateCategoryDetail(String value) {
setSizeMl(Integer.parseInt(value.trim()));
}
}