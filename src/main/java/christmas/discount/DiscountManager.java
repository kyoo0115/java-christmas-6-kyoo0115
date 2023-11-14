package christmas.discount;

import java.util.HashMap;
import java.util.Map;

public class DiscountManager {
    private long totalDiscount;
    private final Map<String, Long> discountDetails = new HashMap<>();

    public long getTotalDiscount() {
        return totalDiscount;
    }

    public Map<String, Long> getDiscountDetails() {
        return discountDetails;
    }
}