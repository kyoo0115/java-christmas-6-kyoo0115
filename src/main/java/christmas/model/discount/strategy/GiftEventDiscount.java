package christmas.model.discount.strategy;

import christmas.model.entity.Order;
import christmas.model.service.GiftService;

import java.time.LocalDate;

public final class GiftEventDiscount implements DiscountStrategy {

    @Override
    public long calculateDiscount(Order order, LocalDate visitDate) {
        if (order.calculateTotalPrice() >= GiftService.GIFT_ELIGIBILITY_THRESHOLD) {
            return GiftService.GIFT_VALUE;
        }
        return 0;
    }
}
