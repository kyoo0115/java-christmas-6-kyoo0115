package christmas.model.service;

import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * PricingService was removed; the total-price calculation now lives on Order.
 * These tests verify Order.calculateTotalPrice() directly.
 */
class OrderPricingTest {

    @Test
    @DisplayName("주문 항목의 총액은 모든 항목의 가격과 수량을 반영해야 함")
    void calculateTotalPrice_ShouldReflectTotalPriceOfItems() {
        MenuItem item1 = mock(MenuItem.class);
        MenuItem item2 = mock(MenuItem.class);
        when(item1.price()).thenReturn(1_000);
        when(item2.price()).thenReturn(500);

        Order order = new Order();
        order.addItem(item1, 2); // 2000
        order.addItem(item2, 3); // 1500

        assertEquals(3_500, order.calculateTotalPrice());
    }
}
