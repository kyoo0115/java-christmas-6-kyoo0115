package christmas.model.service.strategy;

import christmas.model.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.Order;
import java.time.LocalDate;

public class WeekdayDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return discountPolicy.calculateWeekdayDessertDiscount(order.getDessertCount(), visitDate);
    }
}
