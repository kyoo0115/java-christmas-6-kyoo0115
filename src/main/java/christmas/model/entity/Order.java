package christmas.model.entity;

import christmas.model.MenuCategory;
import christmas.model.service.GiftService;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    private final LinkedHashMap<MenuItem, Integer> items = new LinkedHashMap<>();
    private final Map<String, Long> discountDetails = new LinkedHashMap<>();
    private boolean eligibleForGift;

    public void addItem(MenuItem item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public void updateGiftEligibility(boolean eligible) {
        this.eligibleForGift = eligible;
    }

    public boolean isEligibleForGift() {
        return eligibleForGift;
    }

    public void addDiscountDetail(String label, long amount) {
        discountDetails.put(label, amount);
    }

    public Map<String, Long> getDiscountDetails() {
        return Collections.unmodifiableMap(discountDetails);
    }

    /**
     * Total discount including gift value (used for badge calculation and benefit display).
     */
    public long calculateTotalBenefitAmount() {
        return discountDetails.values().stream().mapToLong(Long::longValue).sum();
    }

    /**
     * Pre-discount total (sum of item prices × quantities).
     */
    public long calculateTotalPrice() {
        return items.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().price() * e.getValue())
                .sum();
    }

    /**
     * Post-discount total: pre-discount price minus all discounts except the gift value
     * (the gift is a physical item, not a price reduction on the bill).
     */
    public long calculateDiscountedTotalPrice() {
        long giftDeduction = eligibleForGift ? GiftService.GIFT_VALUE : 0;
        return calculateTotalPrice() - (calculateTotalBenefitAmount() - giftDeduction);
    }

    public int getDessertCount() {
        return countByCategory(MenuCategory.DESSERT);
    }

    public int getMainCount() {
        return countByCategory(MenuCategory.MAIN);
    }

    private int countByCategory(MenuCategory category) {
        return items.entrySet().stream()
                .filter(e -> e.getKey().category() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public Map<MenuItem, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
