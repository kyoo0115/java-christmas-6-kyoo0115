package christmas.model.entity;

public enum EventBadge {
    없음(0),
    별(5000),
    트리(10000),
    산타(20000);

    private final long threshold;

    EventBadge(long threshold) {
        this.threshold = threshold;
    }

    public static EventBadge getBadgeForAmount(long totalBenefitAmount) {
        EventBadge badgeEarned = 없음;
        for (EventBadge badge : EventBadge.values()) {
            if (totalBenefitAmount >= badge.threshold) {
                badgeEarned = badge;
            }
        }
        return badgeEarned;
    }
}
