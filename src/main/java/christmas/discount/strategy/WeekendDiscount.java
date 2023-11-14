package christmas.model.service.strategy;

import christmas.model.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import java.time.LocalDate;

public class WeekendDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return discountPolicy.calculateWeekendMainDiscount(order.getMainCount(), visitDate);
    }
}
