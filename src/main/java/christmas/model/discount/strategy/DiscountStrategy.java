package christmas.model.discount.strategy;

import christmas.model.entity.Order;

import java.time.LocalDate;

public interface DiscountStrategy {
    long calculateDiscount(Order order, LocalDate visitDate);
}
