package christmas.model;

public enum EventBadge {
    NONE(0),
    STAR(5000),
    TREE(10000),
    SANTA(20000);

    private final int threshold;

    EventBadge(int threshold) {
        this.threshold = threshold;
    }

    public static EventBadge getBadgeForAmount(long discountAmount) {
        if (discountAmount >= SANTA.threshold) {
            return SANTA;
        }
        if (discountAmount >= TREE.threshold) {
            return TREE;
        }
        if (discountAmount >= STAR.threshold) {
            return STAR;
        }
        return NONE;
    }

    public int getThreshold() {
        return threshold;
    }
}
