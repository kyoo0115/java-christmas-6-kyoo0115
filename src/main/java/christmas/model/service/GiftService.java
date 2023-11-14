package christmas.model.service;

import christmas.model.entity.Order;
import java.util.Optional;

public class GiftService {
    public static final long GIFT_ELIGIBILITY_THRESHOLD = 120_000;
    public static final long GIFT_VALUE = 25_000;
    public static final String GIFT_ITEM = "샴페인";

    public void processGiftEligibility(Order order) {
        long totalPriceBeforeDiscounts = order.calculateTotalPrice();
        boolean isEligible = totalPriceBeforeDiscounts >= GIFT_ELIGIBILITY_THRESHOLD;
        order.updateGiftEligibility(isEligible);
    }

    public Optional<String> getGiftItem(Order order) {
        return order.isEligibleForGift() ? Optional.of(GIFT_ITEM) : Optional.empty();
    }
}
