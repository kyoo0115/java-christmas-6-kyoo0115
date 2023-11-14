package christmas.discount.strategy;

import christmas.discount.DiscountPolicy;
import christmas.discount.strategy.DiscountStrategy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import java.time.LocalDate;

public class WeekendDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return discountPolicy.calculateWeekendMainDiscount(order.getMainCount(), visitDate);
    }
}
