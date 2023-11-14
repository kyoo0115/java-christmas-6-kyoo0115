package christmas.model.validator;

import christmas.utils.Constants;

import christmas.view.ExceptionView;
import java.util.HashSet;
import java.util.Set;

public class OrderListValidator implements Validator<String> {

    private final Set<String> menuItems;

    public OrderListValidator(Set<String> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String validate(String input) {
        String[] orders = input.split(Constants.ORDER_DELIMITER);
        validateOrders(orders);
        return input;
    }

    private void validateOrders(String[] orders) {
        Set<String> checkedItems = new HashSet<>();
        for (String order : orders) {
            processOrder(order, checkedItems);
        }
    }

    private void processOrder(String order, Set<String> checkedItems) {
        validateOrderFormat(order);
        String item = getItem(order);
        int quantity = getQuantity(order);

        validateMenuItem(item, checkedItems);
        validateQuantity(quantity);

        checkedItems.add(item);
    }

    private void validateOrderFormat(String order) {
        if (order.split(Constants.ITEM_QUANTITY_DELIMITER).length != 2) {
            throw new IllegalArgumentException(ExceptionView.INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private String getItem(String order) {
        return order.split(Constants.ITEM_QUANTITY_DELIMITER)[0].trim();
    }

    private int getQuantity(String order) {
        String quantityStr = order.split(Constants.ITEM_QUANTITY_DELIMITER)[1].trim();
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionView.INVALID_ORDER_FORMAT.getMessage());
        }
    }

    private void validateMenuItem(String item, Set<String> checkedItems) {
        if (!menuItems.contains(item)) {
            throw new IllegalArgumentException(ExceptionView.INVALID_MENU_ITEM.getMessage());
        }
        if (checkedItems.contains(item)) {
            throw new IllegalArgumentException(ExceptionView.DUPLICATE_MENU_ITEM.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ExceptionView.INVALID_QUANTITY.getMessage());
        }
    }
}
