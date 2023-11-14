package christmas;

import christmas.discount.DiscountPolicy;
import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import christmas.utils.DiscountCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {

    @Mock
    private DiscountPolicy discountPolicy;
    @Mock
    private EventDateManager eventDateManager;
    @Mock
    private Order order;

    private DiscountCalculator discountCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        discountCalculator = new DiscountCalculator(discountPolicy, eventDateManager);
    }

    @Test
    @DisplayName("할인이 적용되지 않는 경우 주문은 변경되지 않아야 함")
    void calculateDiscounts_WhenNoDiscounts_ShouldNotModifyOrder() {
        LocalDate visitDate = LocalDate.of(2023, 11, 15);
        when(discountPolicy.calculateChristmasDiscount(visitDate)).thenReturn(0L);
        when(discountPolicy.calculateWeekdayDessertDiscount(anyInt(), eq(visitDate))).thenReturn(0L);

        Order updatedOrder = discountCalculator.calculateDiscounts(order, visitDate);

        verify(order, never()).addDiscountDetail(anyString(), anyLong());
        assertNotNull(updatedOrder);
    }
}
