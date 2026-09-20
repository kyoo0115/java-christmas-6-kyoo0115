package christmas.model.discount;

import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscountTypeTest {

    private Order mockOrder;
    private DiscountPolicy policy;
    private EventDateManager dateManager;
    private LocalDate visitDate;

    @BeforeEach
    void setUp() {
        mockOrder   = mock(Order.class);
        policy      = new DiscountPolicy();
        dateManager = new EventDateManager();
        visitDate   = LocalDate.of(2023, 12, 15);
        when(mockOrder.calculateTotalPrice()).thenReturn(0L);
        when(mockOrder.getDessertCount()).thenReturn(0);
        when(mockOrder.getMainCount()).thenReturn(0);
    }

    @Test
    @DisplayName("createCalculator로 만든 계산기는 모든 할인 타입을 처리해야 함")
    void createCalculator_ShouldHandleAllDiscountTypes() {
        DiscountCalculator calculator = DiscountType.createCalculator(policy, dateManager);
        assertNotNull(calculator);
        // Should not throw; discounts may be zero but the calculator runs
        calculator.calculateDiscounts(mockOrder, visitDate);
    }
}
