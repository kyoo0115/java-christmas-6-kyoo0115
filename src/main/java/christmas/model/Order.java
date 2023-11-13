package christmas.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Order {
    private final Map<MenuItem, Integer> items;
    private boolean isEligibleForGift;
    private long totalDiscount;
    private long giftValue = 0;
    private final Map<String, Long> discountDetails = new HashMap<>();

    public Order() {
        this.items = new HashMap<>();
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

    public void checkAndSetGiftEligibility(long totalPrice) {
        this.isEligibleForGift = totalPrice >= 120_000;
        if (isEligibleForGift) {
            this.giftValue = 25_000;
        }
    }

    public long calculateDiscountedTotalPrice() {
        return calculateTotalPrice() - totalDiscount;
    }

    public Map<String, Long> getDiscountDetails() {
        return discountDetails;
    }
    public Map<MenuItem, Integer> getItems() {
        return new HashMap<>(items);
    }

    public void setTotalDiscount(long totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public long calculateTotalBenefitAmount() {
        return getTotalDiscount() + giftValue;
    }

    public EventBadge calculateBadge() {
        long totalBenefitAmount = calculateTotalBenefitAmount();
        return EventBadge.getBadgeForAmount(totalBenefitAmount);
    }
}
