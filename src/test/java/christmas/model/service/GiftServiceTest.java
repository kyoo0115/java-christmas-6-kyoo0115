package christmas.model.service;

import christmas.model.entity.Order;
import christmas.model.service.GiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GiftServiceTest {

    private GiftService giftService;
    private Order mockOrder;

    @BeforeEach
    void setUp() {
        giftService = new GiftService();
        mockOrder = mock(Order.class);
    }

    @Test
    @DisplayName("주문 총액이 선물 자격 기준을 충족하면 선물 자격이 부여되어야 함")
    void processGiftEligibility_WhenEligible_ShouldUpdateEligibility() {
        when(mockOrder.calculateTotalPrice()).thenReturn(GiftService.GIFT_ELIGIBILITY_THRESHOLD + 1);
        giftService.processGiftEligibility(mockOrder);
        verify(mockOrder).updateGiftEligibility(true);
    }

    @Test
    @DisplayName("주문 총액이 선물 자격 기준 미달이면 선물 자격이 부여되지 않아야 함")
    void processGiftEligibility_WhenNotEligible_ShouldNotUpdateEligibility() {
        when(mockOrder.calculateTotalPrice()).thenReturn(GiftService.GIFT_ELIGIBILITY_THRESHOLD - 1);
        giftService.processGiftEligibility(mockOrder);
        verify(mockOrder).updateGiftEligibility(false);
    }

    @Test
    @DisplayName("선물 자격이 있는 주문은 선물 항목을 반환해야 함")
    void getGiftItem_WhenEligible_ShouldReturnGiftItem() {
        when(mockOrder.isEligibleForGift()).thenReturn(true);
        assertEquals(GiftService.GIFT_ITEM, giftService.getGiftItem(mockOrder).orElse(""));
    }

    @Test
    @DisplayName("선물 자격이 없는 주문은 선물 항목을 반환하지 않아야 함")
    void getGiftItem_WhenNotEligible_ShouldReturnEmpty() {
        when(mockOrder.isEligibleForGift()).thenReturn(false);
        assertTrue(giftService.getGiftItem(mockOrder).isEmpty());
    }
}
