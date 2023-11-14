package christmas.utils;

import christmas.model.MenuCategory;
import christmas.model.entity.MenuItem;
import christmas.model.entity.OrderEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Util {
    public static Map<String, MenuItem> createMenuItemMap(Set<MenuItem> menuItems) {
        return menuItems.stream()
                .collect(Collectors.toMap(MenuItem::name, Function.identity()));
    }

    public static List<OrderEntry> parseOrderEntries(String validatedOrderList) {
        List<OrderEntry> entries = new ArrayList<>();
        String[] orderEntries = validatedOrderList.split(Constants.ORDER_DELIMITER);

        for (String entry : orderEntries) {
            String[] parts = entry.split(Constants.ITEM_QUANTITY_DELIMITER);
            String itemName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            entries.add(new OrderEntry(itemName, quantity));
        }
        return entries;
    }

    public static int countItemsByCategory(Map<MenuItem, Integer> items, MenuCategory category) {
        return items.entrySet().stream()
                .filter(entry -> entry.getKey().category() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
