package christmas.model.entity;

import christmas.model.MenuCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {

    private Order order;
    private MenuItem mockDessert;
    private MenuItem mockMain;

    @BeforeEach
    void setUp() {
        order = new Order();
        mockDessert = mock(MenuItem.class);
        mockMain    = mock(MenuItem.class);
        when(mockDessert.category()).thenReturn(MenuCategory.DESSERT);
        when(mockMain.category()).thenReturn(MenuCategory.MAIN);
    }

    @Test
    @DisplayName("주문에 항목을 추가해야 함")
    void addItem_ShouldAddItemToOrder() {
        order.addItem(mockDessert, 2);
        assertEquals(2, order.getItems().get(mockDessert));
    }

    @Test
    @DisplayName("주문 총액은 모든 항목의 가격을 반영해야 함")
    void calculateTotalPrice_ShouldReflectTotalPriceOfItems() {
        when(mockDessert.price()).thenReturn(500);
        when(mockMain.price()).thenReturn(1_000);
        order.addItem(mockDessert, 2); // 1000
        order.addItem(mockMain, 1);   // 1000
        assertEquals(2_000, order.calculateTotalPrice());
    }

    @Test
    @DisplayName("선물 자격 업데이트 기능이 작동해야 함")
    void updateGiftEligibility_ShouldUpdateEligibility() {
        order.updateGiftEligibility(true);
        assertTrue(order.isEligibleForGift());
    }

    @Test
    @DisplayName("디저트 수량 계산이 정확해야 함")
    void getDessertCount_ShouldReturnCorrectCount() {
        order.addItem(mockDessert, 3);
        assertEquals(3, order.getDessertCount());
    }

    @Test
    @DisplayName("메인 요리 수량 계산이 정확해야 함")
    void getMainCount_ShouldReturnCorrectCount() {
        order.addItem(mockMain, 2);
        assertEquals(2, order.getMainCount());
    }
}
