package christmas.model.service;

import christmas.model.entity.MenuItem;
import christmas.model.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PricingServiceTest {

    private PricingService pricingService;
    private MenuItem mockItem1;
    private MenuItem mockItem2;

    @BeforeEach
    void setUp() {
        pricingService = new PricingService();
        mockItem1 = mock(MenuItem.class);
        mockItem2 = mock(MenuItem.class);
    }

    @Test
    @DisplayName("주문 항목의 총액은 모든 항목의 가격과 수량을 반영해야 함")
    void calculateTotalPrice_ShouldReflectTotalPriceOfItems() {
        Map<MenuItem, Integer> items = new HashMap<>();
        when(mockItem1.price()).thenReturn(1000);
        when(mockItem2.price()).thenReturn(500);
        items.put(mockItem1, 2); // 2000
        items.put(mockItem2, 3); // 1500
        assertEquals(3500, pricingService.calculateTotalPrice(items));
    }
}
