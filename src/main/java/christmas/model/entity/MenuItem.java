package christmas.model.entity;

import christmas.model.MenuCategory;
import christmas.utils.Constants;
import java.util.Objects;

public record MenuItem(String name, int price, MenuCategory category) {
    public MenuItem {
        validate(name, price, category);
    }

    private void validate(String name, int price, MenuCategory category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty.");
        }
        if (price < Constants.ZERO) {
            throw new IllegalArgumentException("Item price cannot be negative.");
        }
        Objects.requireNonNull(category, "Item category cannot be null.");
    }
}
