package christmas.model.service;

import christmas.model.MenuCategory;
import christmas.model.entity.MenuItem;

import java.util.Set;

/**
 * Provides the fixed menu for the December 2023 event.
 * Extracted as a service to keep the controller thin.
 */
public class MenuLoader {

    private MenuLoader() {
    }

    public static Set<MenuItem> loadMenuItems() {
        return Set.of(
                new MenuItem("양송이수프", 6_000, MenuCategory.APPETIZER),
                new MenuItem("타파스", 5_500, MenuCategory.APPETIZER),
                new MenuItem("시저샐러드", 8_000, MenuCategory.APPETIZER),
                new MenuItem("티본스테이크", 55_000, MenuCategory.MAIN),
                new MenuItem("바비큐립", 54_000, MenuCategory.MAIN),
                new MenuItem("해산물파스타", 35_000, MenuCategory.MAIN),
                new MenuItem("크리스마스파스타", 25_000, MenuCategory.MAIN),
                new MenuItem("초코케이크", 15_000, MenuCategory.DESSERT),
                new MenuItem("아이스크림", 5_000, MenuCategory.DESSERT),
                new MenuItem("제로콜라", 3_000, MenuCategory.BEVERAGE),
                new MenuItem("레드와인", 60_000, MenuCategory.BEVERAGE),
                new MenuItem("샴페인", 25_000, MenuCategory.BEVERAGE)  // fixed: was "샴폐인"
        );
    }
}
