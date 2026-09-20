package christmas.model.discount.strategy;

import christmas.model.EventDateManager;
import christmas.model.discount.DiscountPolicy;
import christmas.model.entity.Order;

import java.time.LocalDate;

public final class SpecialDiscount implements DiscountStrategy {

    private final EventDateManager eventDateManager;

    public SpecialDiscount(EventDateManager eventDateManager) {
        this.eventDateManager = eventDateManager;
    }

    @Override
    public long calculateDiscount(Order order, LocalDate visitDate) {
        return eventDateManager.isSpecialOfferDate(visitDate) ? DiscountPolicy.SPECIAL_DISCOUNT : 0;
    }
}
