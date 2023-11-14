package christmas.utils;

import christmas.model.DiscountPolicy;
import christmas.model.DiscountType;
import christmas.model.EventDateManager;
import christmas.model.MenuCategory;
import christmas.model.MenuItem;
import christmas.model.Order;
import christmas.model.service.GiftService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OrderCalculator {
    private final DiscountPolicy discountPolicy;
    private final EventDateManager eventDateManager;
    private final GiftService giftService;

    public OrderCalculator(DiscountPolicy discountPolicy, EventDateManager eventDateManager, GiftService giftService) {
        this.discountPolicy = discountPolicy;
        this.eventDateManager = eventDateManager;
        this.giftService = giftService;
    }

    public long calculateTotalPrice(Order order) {
        return order.getItems().entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().price() * entry.getValue())
                .sum();
    }

    public void applyDiscounts(Order order, LocalDate visitDate) {
        // Apply each type of discount
        for (DiscountType discountType : DiscountType.values()) {
            long discountAmount = discountType.calculateDiscount(order, visitDate, discountPolicy, eventDateManager);
            if (discountAmount > 0) {
                order.addDiscountDetail(discountType.toString(), discountAmount);
            }
        }

        // After applying all discounts, check if the order is eligible for a gift
        giftService.determineGiftEligibility(order, this);
    }
    public Map<String, Long> getDiscountDetails() {
        return discountDetails;
    }

    public void addDiscountDetail(String detail, long amount) {
        discountDetails.put(detail, amount);
        totalDiscount += amount;
    }

    public long getTotalDiscount() {
        return totalDiscount;
    }

    public int countItemsByCategory(Order order, MenuCategory category) {
        return order.getItems().entrySet().stream()
                .filter(entry -> entry.getKey().category() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}