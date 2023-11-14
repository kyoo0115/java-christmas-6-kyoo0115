package christmas.model.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class EventBadgeTest {

    @Test
    @DisplayName("총 혜택 금액이 없을 때 '없음' 배지가 부여되어야 함")
    void getBadgeForAmount_NoBenefits_ShouldReturnNoBadge() {
        assertEquals(EventBadge.없음, EventBadge.getBadgeForAmount(0));
    }

    @Test
    @DisplayName("총 혜택 금액이 별 배지 기준에 도달할 때 '별' 배지가 부여되어야 함")
    void getBadgeForAmount_StarThreshold_ShouldReturnStarBadge() {
        assertEquals(EventBadge.별, EventBadge.getBadgeForAmount(5000));
        assertEquals(EventBadge.별, EventBadge.getBadgeForAmount(9999));
    }

    @Test
    @DisplayName("총 혜택 금액이 트리 배지 기준에 도달할 때 '트리' 배지가 부여되어야 함")
    void getBadgeForAmount_TreeThreshold_ShouldReturnTreeBadge() {
        assertEquals(EventBadge.트리, EventBadge.getBadgeForAmount(10000));
        assertEquals(EventBadge.트리, EventBadge.getBadgeForAmount(19999));
    }

    @Test
    @DisplayName("총 혜택 금액이 산타 배지 기준에 도달할 때 '산타' 배지가 부여되어야 함")
    void getBadgeForAmount_SantaThreshold_ShouldReturnSantaBadge() {
        assertEquals(EventBadge.산타, EventBadge.getBadgeForAmount(20000));
        assertEquals(EventBadge.산타, EventBadge.getBadgeForAmount(30000));
    }
}
