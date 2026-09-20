package christmas.model.service;

import christmas.model.MenuCategory;
import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;
import christmas.model.entity.OrderEntry;
import christmas.model.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {

    private static final String ORDER_DELIMITER         = ",";
    private static final String ITEM_QUANTITY_DELIMITER = "-";
    private static final int    MAX_TOTAL_QUANTITY      = 20;

    private final Map<String, MenuItem> menuItemMap;

    public OrderService(Set<MenuItem> menuItems) {
        this.menuItemMap = menuItems.stream()
                .collect(Collectors.toMap(MenuItem::name, Function.identity()));
    }

    public Order createOrderFromInput(String validatedOrderList) {
        Order order = new Order();
        parseOrderEntries(validatedOrderList)
                .forEach(entry -> order.addItem(menuItemMap.get(entry.itemName()), entry.quantity()));
        validateOrder(order);
        return order;
    }

    private void validateOrder(Order order) {
        validateTotalQuantity(order);
        validateNotBeverageOnly(order);
    }

    private void validateTotalQuantity(Order order) {
        int total = order.getItems().values().stream().mapToInt(Integer::intValue).sum();
        if (total > MAX_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateNotBeverageOnly(Order order) {
        boolean hasNonBeverage = order.getItems().keySet().stream()
                .anyMatch(item -> item.category() != MenuCategory.BEVERAGE);
        if (!hasNonBeverage) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private List<OrderEntry> parseOrderEntries(String orderList) {
        return Arrays.stream(orderList.split(ORDER_DELIMITER))
                .map(String::trim)
                .map(entry -> {
                    String[] parts = entry.split(ITEM_QUANTITY_DELIMITER);
                    return new OrderEntry(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                })
                .toList();
    }
}
