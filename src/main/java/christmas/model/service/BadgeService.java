package christmas.model.service;

import christmas.model.EventBadge;
import christmas.model.Order;

public class BadgeService {
    public EventBadge determineBadge(Order order) {
        long totalBenefitAmount = order.calculateTotalBenefitAmount();
        return EventBadge.getBadgeForAmount(totalBenefitAmount);
    }
}
