package christmas.model.discount.strategy;

import christmas.model.discount.DiscountPolicy;
import christmas.model.entity.Order;

import java.time.LocalDate;

public final class WeekendDiscount implements DiscountStrategy {

    private final DiscountPolicy discountPolicy;

    public WeekendDiscount(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public long calculateDiscount(Order order, LocalDate visitDate) {
        return discountPolicy.calculateWeekendMainDiscount(order.getMainCount(), visitDate);
    }
}
