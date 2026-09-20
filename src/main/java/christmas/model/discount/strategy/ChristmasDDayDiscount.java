package christmas.model.discount.strategy;

import christmas.model.discount.DiscountPolicy;
import christmas.model.entity.Order;

import java.time.LocalDate;

public final class ChristmasDDayDiscount implements DiscountStrategy {

    private final DiscountPolicy discountPolicy;

    public ChristmasDDayDiscount(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public long calculateDiscount(Order order, LocalDate visitDate) {
        return discountPolicy.calculateChristmasDiscount(visitDate);
    }
}
