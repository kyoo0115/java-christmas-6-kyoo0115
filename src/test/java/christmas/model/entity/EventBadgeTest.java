package christmas.model.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventBadgeTest {

    @Test
    @DisplayName("총 혜택 금액이 없을 때 '없음' 배지가 부여되어야 함")
    void getBadgeForAmount_NoBenefits_ShouldReturnNoBadge() {
        assertEquals(EventBadge.NONE, EventBadge.getBadgeForAmount(0));
    }

    @Test
    @DisplayName("총 혜택 금액이 별 배지 기준에 도달할 때 '별' 배지가 부여되어야 함")
    void getBadgeForAmount_StarThreshold_ShouldReturnStarBadge() {
        assertEquals(EventBadge.STAR, EventBadge.getBadgeForAmount(5_000));
        assertEquals(EventBadge.STAR, EventBadge.getBadgeForAmount(9_999));
    }

    @Test
    @DisplayName("총 혜택 금액이 트리 배지 기준에 도달할 때 '트리' 배지가 부여되어야 함")
    void getBadgeForAmount_TreeThreshold_ShouldReturnTreeBadge() {
        assertEquals(EventBadge.TREE, EventBadge.getBadgeForAmount(10_000));
        assertEquals(EventBadge.TREE, EventBadge.getBadgeForAmount(19_999));
    }

    @Test
    @DisplayName("총 혜택 금액이 산타 배지 기준에 도달할 때 '산타' 배지가 부여되어야 함")
    void getBadgeForAmount_SantaThreshold_ShouldReturnSantaBadge() {
        assertEquals(EventBadge.SANTA, EventBadge.getBadgeForAmount(20_000));
        assertEquals(EventBadge.SANTA, EventBadge.getBadgeForAmount(30_000));
    }
}
