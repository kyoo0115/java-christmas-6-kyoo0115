package christmas.model.service;

import christmas.model.MenuCategory;
import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        Set<MenuItem> menu = Set.of(
                new MenuItem("해산물파스타", 35_000, MenuCategory.MAIN),
                new MenuItem("초코케이크",   15_000, MenuCategory.DESSERT),
                new MenuItem("레드와인",     60_000, MenuCategory.BEVERAGE),
                new MenuItem("제로콜라",      3_000, MenuCategory.BEVERAGE)
        );
        orderService = new OrderService(menu);
    }

    @Test
    @DisplayName("유효한 주문 문자열로 Order를 생성해야 함")
    void createOrderFromInput_ShouldBuildOrderCorrectly() {
        Order order = orderService.createOrderFromInput("해산물파스타-2,초코케이크-1");
        assertNotNull(order);
        assertEquals(2 * 35_000 + 15_000, order.calculateTotalPrice());
    }

    @Test
    @DisplayName("카테고리별 수량 계산이 Order에서 동작해야 함")
    void categoryCount_ShouldWorkOnOrder() {
        Order order = orderService.createOrderFromInput("해산물파스타-2,초코케이크-3");
        assertEquals(2, order.getMainCount());
        assertEquals(3, order.getDessertCount());
    }

    @Test
    @DisplayName("총 수량이 20개를 초과하면 예외를 발생시켜야 함")
    void whenTotalQuantityExceeds20_thenException() {
        assertThrows(IllegalArgumentException.class,
                () -> orderService.createOrderFromInput("해산물파스타-10,초코케이크-11"));
    }

    @Test
    @DisplayName("음료만 주문하면 예외를 발생시켜야 함")
    void whenBeverageOnly_thenException() {
        assertThrows(IllegalArgumentException.class,
                () -> orderService.createOrderFromInput("레드와인-1,제로콜라-1"));
    }
}
