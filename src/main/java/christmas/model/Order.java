package christmas.model;

import java.util.Map;
import java.util.HashMap;

public class Order {
    private final Map<MenuItem, Integer> items;

    public Order() {
        this.items = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public int getTotalItemCount() {
        return items.values().stream().reduce(0, Integer::sum);
    }

    public boolean containsItem(MenuItem item) {
        return items.containsKey(item);
    }

    public long calculateTotalPrice() {
        return items.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public boolean containsOnlyBeverages() {
        return items.keySet().stream().allMatch(item -> item.getCategory() == MenuCategory.BEVERAGE);
    }
}
