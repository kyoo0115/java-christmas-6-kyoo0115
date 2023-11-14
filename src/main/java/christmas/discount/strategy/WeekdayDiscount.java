package christmas.discount.strategy;

import christmas.discount.DiscountPolicy;
import christmas.discount.strategy.DiscountStrategy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import java.time.LocalDate;

public class WeekdayDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return discountPolicy.calculateWeekdayDessertDiscount(order.getDessertCount(), visitDate);
    }
}
