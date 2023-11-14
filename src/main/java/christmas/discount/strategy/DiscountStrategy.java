package christmas.discount.strategy;

import christmas.discount.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import java.time.LocalDate;

public interface DiscountStrategy {
    long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager);
}
