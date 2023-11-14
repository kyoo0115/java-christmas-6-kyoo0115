package christmas.discount;

import christmas.discount.DiscountPolicy;
import christmas.discount.DiscountType;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTypeTest {

    private Order mockOrder;
    private DiscountPolicy mockDiscountPolicy;
    private EventDateManager mockEventDateManager;
    private LocalDate visitDate;

    @BeforeEach
    void setUp() {
        mockOrder = mock(Order.class);
        mockDiscountPolicy = mock(DiscountPolicy.class);
        mockEventDateManager = mock(EventDateManager.class);
        visitDate = LocalDate.of(2023, 12, 15);
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 계산이 제대로 작동해야 함")
    void calculateDiscount_ChristmasDDay_ShouldCalculateCorrectly() {
        long discount = DiscountType.CHRISTMAS_D_DAY.calculateDiscount(mockOrder, visitDate, mockDiscountPolicy, mockEventDateManager);
        assertNotNull(discount);
    }

    @Test
    @DisplayName("평일 할인 계산이 제대로 작동해야 함")
    void calculateDiscount_WeekdayDiscount_ShouldCalculateCorrectly() {
        long discount = DiscountType.WEEKDAY_DISCOUNT.calculateDiscount(mockOrder, visitDate, mockDiscountPolicy, mockEventDateManager);
        assertNotNull(discount);
    }

    @Test
    @DisplayName("주말 할인 계산이 제대로 작동해야 함")
    void calculateDiscount_WeekendDiscount_ShouldCalculateCorrectly() {
        long discount = DiscountType.WEEKEND_DISCOUNT.calculateDiscount(mockOrder, visitDate, mockDiscountPolicy, mockEventDateManager);
        assertNotNull(discount);
    }

    @Test
    @DisplayName("특별 할인 계산이 제대로 작동해야 함")
    void calculateDiscount_SpecialDiscount_ShouldCalculateCorrectly() {
        long discount = DiscountType.SPECIAL_DISCOUNT.calculateDiscount(mockOrder, visitDate, mockDiscountPolicy, mockEventDateManager);
        assertNotNull(discount);
    }

    @Test
    @DisplayName("증정 할인 계산이 제대로 작동해야 함")
    void calculateDiscount_GiftEvent_ShouldCalculateCorrectly() {
        long discount = DiscountType.GIFT_EVENT.calculateDiscount(mockOrder, visitDate, mockDiscountPolicy, mockEventDateManager);
        assertNotNull(discount);
    }
}
