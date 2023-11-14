package christmas.model.service.strategy;

import static christmas.model.DiscountPolicy.SPECIAL_DISCOUNT;

import christmas.model.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.Order;
import java.time.LocalDate;

public class SpecialDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return eventDateManager.isSpecialOfferDate(visitDate) ? SPECIAL_DISCOUNT : 0;
    }
}
