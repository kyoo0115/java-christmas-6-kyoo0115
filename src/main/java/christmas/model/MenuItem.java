package christmas.model;

public class MenuItem {
    private final String name;
    private final int price;
    private final MenuCategory category;

    public MenuItem(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public boolean isCategory(MenuCategory otherCategory) {
        return this.category == otherCategory;
    }

    public String displayInfo() {
        return String.format("%s - %d원, %s", name, price, category);
    }

    public int calculateDiscountedPrice(int discountAmount) {
        return price - discountAmount;
    }
}
