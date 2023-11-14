package christmas.model.service.strategy;

import christmas.model.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import christmas.model.service.GiftService;
import java.time.LocalDate;

public class GiftEventDiscount implements DiscountStrategy {
    @Override
    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        if (order.calculateTotalPriceBeforeDiscounts() >= GiftService.GIFT_ELIGIBILITY_THRESHOLD) {
            return GiftService.GIFT_VALUE;
        }
        return 0;
    }
}

