package christmas.model.service.strategy;

import christmas.model.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.Order;
import java.time.LocalDate;

public interface DiscountStrategy {
    long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager);
}
