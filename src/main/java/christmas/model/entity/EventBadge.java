package christmas.model.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String displayName;
    private final long threshold;

    EventBadge(String displayName, long threshold) {
        this.displayName = displayName;
        this.threshold = threshold;
    }

    public static EventBadge getBadgeForAmount(long totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> totalBenefitAmount >= badge.threshold)
                .max(Comparator.comparingLong(badge -> badge.threshold))
                .orElse(NONE);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
