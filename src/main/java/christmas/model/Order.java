package christmas.model;

import christmas.model.service.GiftService;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Order {
    private final LinkedHashMap<MenuItem, Integer> items;
    private boolean isEligibleForGift;
    private long totalDiscount;
    private final Map<String, Long> discountDetails = new HashMap<>();

    public Order() {
        this.items = new LinkedHashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public long calculateTotalPrice() {
        return items.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().price() * entry.getValue())
                .sum();
    }

    public void addDiscountDetail(String detail, long amount) {
        discountDetails.put(detail, amount);
        totalDiscount += amount;
    }

    public boolean isEligibleForGift() {
        return isEligibleForGift;
    }

    public long getTotalDiscount() {
        return totalDiscount;
    }

    public int getDessertCount() {
        return this.getItems().entrySet().stream()
                .filter(entry -> entry.getKey().category() == MenuCategory.DESSERT)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getMainCount() {
        return this.getItems().entrySet().stream()
                .filter(entry -> entry.getKey().category() == MenuCategory.MAIN)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public long calculateDiscountedTotalPrice() {
        long totalPriceBeforeDiscounts = calculateTotalPriceBeforeDiscounts();
        long discountsExcludingGift = getTotalDiscount() - (isEligibleForGift() ? GiftService.GIFT_VALUE : 0);
        return totalPriceBeforeDiscounts - discountsExcludingGift;
    }

    public Map<String, Long> getDiscountDetails() {
        return discountDetails;
    }

    public void setGiftEligibility(boolean isEligibleForGift) {
        this.isEligibleForGift = isEligibleForGift;
    }

    public long calculateTotalBenefitAmount() {
        return getTotalDiscount();
    }

    public long calculateTotalPriceBeforeDiscounts() {
        return items.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().price() * entry.getValue())
                .sum();
    }

    public Map<MenuItem, Integer> getItems() {
        return new LinkedHashMap<>(items);
    }
}