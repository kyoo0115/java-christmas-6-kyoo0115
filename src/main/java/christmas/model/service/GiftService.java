package christmas.model.service;

import christmas.model.Order;
import java.util.Optional;

public class GiftService {
    public static final long GIFT_ELIGIBILITY_THRESHOLD = 120_000;
    public static final long GIFT_VALUE = 25_000;
    public static final String GIFT_ITEM = "샴폐인";

    public boolean checkGiftEligibility(Order order) {
        long totalPriceBeforeDiscounts = order.calculateTotalPriceBeforeDiscounts();
        boolean isEligibleGift = totalPriceBeforeDiscounts >= GIFT_ELIGIBILITY_THRESHOLD;
        order.setGiftEligibility(isEligibleGift);
        return isEligibleGift;
    }

    public Optional<String> getGiftItem(Order order) {
        return order.isEligibleForGift() ? Optional.of(GIFT_ITEM) : Optional.empty();
    }
}