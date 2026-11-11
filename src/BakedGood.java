public class BakedGood extends Product {
    private int shelfLifeDays;
    public BakedGood(String id, String name, double unitPrice, int quantity,
                     int reorderLevel, int shelfLifeDays) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setShelfLifeDays(shelfLifeDays);
    }

     public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays < 1) {
            throw new IllegalArgumentException("Shelf life must be at least 1 day.");
        }
        this.shelfLifeDays = shelfLifeDays;
    }

     @Override
    public String getCategory() {
        return "Baked Good";
    }
    @Override
    public String getCategoryDetails() {
        return "Shelf life: " + shelfLifeDays + " day(s)";
    }
    @Override
    public String getDetailPrompt() {
        return "Enter new shelf life in days: ";
    }
     @Override
public void updateCategoryDetail(String value) {
setShelfLifeDays(Integer.parseInt(value.trim()));
}
}