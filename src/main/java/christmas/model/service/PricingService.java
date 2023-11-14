package christmas.model.service;

import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;

import java.util.Map;

public class PricingService {

    public long calculateTotalPrice(Map<MenuItem, Integer> items) {
        return items.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().price() * entry.getValue())
                .sum();
    }
}
