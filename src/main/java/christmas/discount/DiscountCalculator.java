package christmas.discount;

import christmas.discount.DiscountPolicy;
import christmas.discount.DiscountType;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import christmas.utils.Constants;
import java.time.LocalDate;

public class DiscountCalculator {

    private final DiscountPolicy discountPolicy;
    private final EventDateManager eventDateManager;

    public DiscountCalculator(DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        this.discountPolicy = discountPolicy;
        this.eventDateManager = eventDateManager;
    }

    public Order calculateDiscounts(Order order, LocalDate visitDate) {
        for (DiscountType discountType : DiscountType.values()) {
            long discountAmount = discountType.calculateDiscount(order, visitDate, discountPolicy, eventDateManager);
            if (discountAmount > Constants.ZERO) {
                order.addDiscountDetail(discountType.toString(), discountAmount);
            }
        }
        return order;
    }
}
