package christmas;

import christmas.model.MenuCategory;
import christmas.model.entity.MenuItem;
import christmas.model.entity.OrderEntry;
import christmas.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    private Set<MenuItem> menuItems;

    @BeforeEach
    void setUp() {
        MenuItem item1 = new MenuItem("해산물파스타", 1500, MenuCategory.MAIN);
        MenuItem item2 = new MenuItem("초코케이크", 500, MenuCategory.DESSERT);
        MenuItem item3 = new MenuItem("레드와인", 800, MenuCategory.BEVERAGE);
        menuItems = Set.of(item1, item2, item3);
    }

    @Test
    @DisplayName("메뉴 항목 맵 생성이 제대로 작동해야 함")
    void createMenuItemMap_ShouldCreateMapCorrectly() {
        Map<String, MenuItem> menuItemMap = Util.createMenuItemMap(menuItems);
        assertEquals(3, menuItemMap.size());
        assertTrue(menuItemMap.containsKey("해산물파스타"));
        assertTrue(menuItemMap.containsKey("초코케이크"));
        assertTrue(menuItemMap.containsKey("레드와인"));
    }

    @Test
    @DisplayName("주문 항목 파싱이 제대로 작동해야 함")
    void parseOrderEntries_ShouldParseEntriesCorrectly() {
        String validatedOrderList = "해산물파스타-2, 레드와인-1, 초코케이크-3";
        List<OrderEntry> orderEntries = Util.parseOrderEntries(validatedOrderList);
        assertEquals(3, orderEntries.size());
        assertEquals("해산물파스타", orderEntries.get(0).itemName());
        assertEquals(2, orderEntries.get(0).quantity());
        assertEquals("레드와인", orderEntries.get(1).itemName());
        assertEquals(1, orderEntries.get(1).quantity());
        assertEquals("초코케이크", orderEntries.get(2).itemName());
        assertEquals(3, orderEntries.get(2).quantity());
    }

    @Test
    @DisplayName("카테고리별 아이템 수량 계산이 제대로 작동해야 함")
    void countItemsByCategory_ShouldCountItemsCorrectly() {
        Map<MenuItem, Integer> items = new HashMap<>();
        items.put(new MenuItem("해산물파스타", 1500, MenuCategory.MAIN), 2);
        items.put(new MenuItem("레드와인", 800, MenuCategory.BEVERAGE), 3);
        items.put(new MenuItem("초코케이크", 500, MenuCategory.DESSERT), 1);

        int mainCount = Util.countItemsByCategory(items, MenuCategory.MAIN);
        int beverageCount = Util.countItemsByCategory(items, MenuCategory.BEVERAGE);
        int dessertCount = Util.countItemsByCategory(items, MenuCategory.DESSERT);

        assertEquals(2, mainCount);
        assertEquals(3, beverageCount);
        assertEquals(1, dessertCount);
    }
}
