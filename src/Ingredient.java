public class Ingredient extends Product {
    private String unit;

    public Ingredient(String id, String name, double unitPrice, int quantity,
                      int reorderLevel, String unit) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setUnit(unit);
    }

    public void setUnit(String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }
        this.unit = unit.trim();
    }

    @Override
    public String getCategory() {
        return "Ingredient";
    }

    @Override
    public String getCategoryDetails() {
        return "Unit: " + unit;
    }

    @Override
    public String getDetailPrompt() {
        return "Enter new unit (for example kg or L): ";
    }
    
    @Override
    public void updateCategoryDetail(String value) {
        setUnit(value);
    }
}