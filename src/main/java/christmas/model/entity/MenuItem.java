package christmas.model.entity;

import christmas.model.MenuCategory;

public record MenuItem(String name, int price, MenuCategory category) {
    public MenuItem {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Item price cannot be negative.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Item category cannot be null.");
        }
    }
}
