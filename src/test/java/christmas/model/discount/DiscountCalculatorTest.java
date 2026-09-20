package christmas.model.discount;

import christmas.model.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;
    private Order mockOrder;

    @BeforeEach
    void setUp() {
        var policy      = new DiscountPolicy();
        var dateManager = new christmas.model.EventDateManager();
        discountCalculator = DiscountType.createCalculator(policy, dateManager);
        mockOrder = mock(Order.class);
    }

    @Test
    @DisplayName("할인이 적용되지 않는 경우 주문은 변경되지 않아야 함")
    void calculateDiscounts_WhenNoDiscounts_ShouldNotModifyOrder() {
        // Date outside all event windows: November has no discounts
        LocalDate visitDate = LocalDate.of(2023, 11, 15);
        when(mockOrder.calculateTotalPrice()).thenReturn(0L);
        when(mockOrder.getDessertCount()).thenReturn(0);
        when(mockOrder.getMainCount()).thenReturn(0);

        Order result = discountCalculator.calculateDiscounts(mockOrder, visitDate);

        verify(mockOrder, never()).addDiscountDetail(anyString(), anyLong());
        assertNotNull(result);
    }
}
