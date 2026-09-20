package christmas.discount;

import java.util.HashMap;
import java.util.Map;

public class DiscountManager {
    private final Map<String, Long> discountDetails = new HashMap<>();
    private long totalDiscount;

    public long getTotalDiscount() {
        return totalDiscount;
    }

    public Map<String, Long> getDiscountDetails() {
        return discountDetails;
    }
}