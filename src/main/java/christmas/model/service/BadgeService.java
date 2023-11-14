package christmas.model.service;

import christmas.model.entity.EventBadge;
import christmas.model.entity.Order;

public class BadgeService {
    public EventBadge determineBadge(Order order) {
        long totalBenefitAmount = order.calculateTotalBenefitAmount();
        return EventBadge.getBadgeForAmount(totalBenefitAmount);
    }
}
