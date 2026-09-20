package christmas.model.validator;

import christmas.model.exception.ErrorMessage;

import java.util.HashSet;
import java.util.Set;

public class OrderListValidator implements Validator<String> {

    private static final String ORDER_DELIMITER         = ",";
    private static final String ITEM_QUANTITY_DELIMITER = "-";

    private final Set<String> validMenuNames;

    public OrderListValidator(Set<String> validMenuNames) {
        this.validMenuNames = validMenuNames;
    }

    @Override
    public String validate(String input) {
        String[] orders = input.split(ORDER_DELIMITER);
        Set<String> seen = new HashSet<>();
        for (String order : orders) {
            validateSingleOrder(order.trim(), seen);
        }
        return input;
    }

    private void validateSingleOrder(String order, Set<String> seen) {
        String[] parts = order.split(ITEM_QUANTITY_DELIMITER);
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        String item     = parts[0].trim();
        int    quantity = parseQuantity(parts[1].trim());

        if (!validMenuNames.contains(item)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (seen.contains(item)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (quantity < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        seen.add(item);
    }

    private int parseQuantity(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
