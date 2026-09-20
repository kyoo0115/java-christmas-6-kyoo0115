package christmas.model.discount;

import christmas.model.discount.strategy.DiscountStrategy;
import christmas.model.entity.Order;

import java.time.LocalDate;
import java.util.Map;

public class DiscountCalculator {

    private static final long MIN_ORDER_AMOUNT_FOR_EVENT = 10_000;

    private final Map<DiscountType, DiscountStrategy> strategies;

    DiscountCalculator(Map<DiscountType, DiscountStrategy> strategies) {
        this.strategies = Map.copyOf(strategies);
    }

    public Order calculateDiscounts(Order order, LocalDate visitDate) {
        if (order.calculateTotalPrice() < MIN_ORDER_AMOUNT_FOR_EVENT) {
            return order;
        }
        for (DiscountType type : DiscountType.values()) {
            DiscountStrategy strategy = strategies.get(type);
            long amount = strategy.calculateDiscount(order, visitDate);
            if (amount > 0) {
                order.addDiscountDetail(type.toString(), amount);
            }
        }
        return order;
    }
}
