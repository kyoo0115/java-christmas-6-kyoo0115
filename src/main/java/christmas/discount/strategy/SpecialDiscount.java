package christmas.discount.strategy;

import static christmas.discount.DiscountPolicy.SPECIAL_DISCOUNT;

import christmas.discount.DiscountPolicy;
import christmas.discount.strategy.DiscountStrategy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import java.time.LocalDate;

public class SpecialDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return eventDateManager.isSpecialOfferDate(visitDate) ? SPECIAL_DISCOUNT : 0;
    }
}
