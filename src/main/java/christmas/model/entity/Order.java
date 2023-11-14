package christmas.model.entity;

import christmas.discount.DiscountManager;
import christmas.model.MenuCategory;
import christmas.model.service.GiftService;
import christmas.model.service.PricingService;
import christmas.utils.Util;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

public class Order {
    private final LinkedHashMap<MenuItem, Integer> items;
    private boolean isEligibleForGift;
    private final Map<String, Long> discountDetails = new HashMap<>();
    private final PricingService pricingService = new PricingService();
    private final DiscountManager discountManager = new DiscountManager();


    public Order() {
        this.items = new LinkedHashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public void updateGiftEligibility(boolean eligibility) {
        this.isEligibleForGift = eligibility;
    }

    public long calculateTotalPrice() {
        return pricingService.calculateTotalPrice(this.items);
    }

    public void addDiscountDetail(String detail, long amount) {
        discountDetails.put(detail, amount);
    }

    public boolean isEligibleForGift() {
        return isEligibleForGift;
    }

    public long getTotalDiscount() {
        return discountManager.getTotalDiscount();
    }

    public int getDessertCount() {
        return Util.countItemsByCategory(this.items, MenuCategory.DESSERT);
    }

    public int getMainCount() {
        return Util.countItemsByCategory(this.items, MenuCategory.MAIN);
    }

    public long calculateDiscountedTotalPrice() {
        long totalPriceBeforeDiscounts = calculateTotalPriceBeforeDiscounts();
        long discountsExcludingGift = getTotalDiscount() - (isEligibleForGift() ? GiftService.GIFT_VALUE : 0);
        return totalPriceBeforeDiscounts - discountsExcludingGift;
    }

    public Map<String, Long> getDiscountDetails() {
        return discountManager.getDiscountDetails();
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