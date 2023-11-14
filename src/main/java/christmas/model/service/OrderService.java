package christmas.model.service;

import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;
import christmas.model.entity.OrderEntry;
import christmas.utils.Util;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {

    private final Set<MenuItem> menuItems;

    public OrderService(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    public Order createOrderFromInput(String validatedOrderList) {
        Order order = new Order();
        Map<String, MenuItem> menuItemMap = Util.createMenuItemMap(menuItems);
        List<OrderEntry> orderEntries = Util.parseOrderEntries(validatedOrderList);

        for (OrderEntry entry : orderEntries) {
            MenuItem item = menuItemMap.get(entry.itemName());
            order.addItem(item, entry.quantity());
        }
        return order;
    }
}
