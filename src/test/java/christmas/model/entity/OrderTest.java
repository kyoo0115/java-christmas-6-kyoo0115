package christmas;

import christmas.model.MenuCategory;
import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderTest {

    private Order order;
    private MenuItem mockItemDessert;
    private MenuItem mockItemMain;

    @BeforeEach
    void setUp() {
        order = new Order();
        mockItemDessert = mock(MenuItem.class);
        mockItemMain = mock(MenuItem.class);

        when(mockItemDessert.category()).thenReturn(MenuCategory.DESSERT);
        when(mockItemMain.category()).thenReturn(MenuCategory.MAIN);
    }

    @Test
    @DisplayName("주문에 항목을 추가해야 함")
    void addItem_ShouldAddItemToOrder() {
        order.addItem(mockItemDessert, 2);
        assertEquals(2, order.getItems().get(mockItemDessert));
    }

    @Test
    @DisplayName("주문 총액은 모든 항목의 가격을 반영해야 함")
    void calculateTotalPrice_ShouldReflectTotalPriceOfItems() {
        when(mockItemDessert.price()).thenReturn(500);
        when(mockItemMain.price()).thenReturn(1000);
        order.addItem(mockItemDessert, 2); // 1000
        order.addItem(mockItemMain, 1); // 1000
        assertEquals(2000, order.calculateTotalPrice());
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
        order.addItem(mockItemDessert, 3);
        assertEquals(3, order.getDessertCount());
    }

    @Test
    @DisplayName("메인 요리 수량 계산이 정확해야 함")
    void getMainCount_ShouldReturnCorrectCount() {
        order.addItem(mockItemMain, 2);
        assertEquals(2, order.getMainCount());
    }
}
