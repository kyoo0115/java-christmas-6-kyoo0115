package christmas.utils;

import christmas.model.MenuCategory;
import christmas.model.MenuItem;

import java.util.HashSet;
import java.util.Set;

public class MenuLoader {
    public static Set<MenuItem> loadMenuItems() {
        Set<MenuItem> menuItems = new HashSet<>();
        menuItems.add(new MenuItem("양송이수프", 6000, MenuCategory.APPETIZER));
        menuItems.add(new MenuItem("타파스", 5500, MenuCategory.APPETIZER));
        menuItems.add(new MenuItem("시저샐러드", 8000, MenuCategory.APPETIZER));
        menuItems.add(new MenuItem("티본스테이크", 55000, MenuCategory.MAIN));
        menuItems.add(new MenuItem("바비큐립", 54000, MenuCategory.MAIN));
        menuItems.add(new MenuItem("해산물파스타", 35000, MenuCategory.MAIN));
        menuItems.add(new MenuItem("크리스마스파스타", 25000, MenuCategory.MAIN));
        menuItems.add(new MenuItem("초코케이크", 15000, MenuCategory.DESSERT));
        menuItems.add(new MenuItem("아이스크림", 5000, MenuCategory.DESSERT));
        menuItems.add(new MenuItem("제로콜라", 3000, MenuCategory.BEVERAGE));
        menuItems.add(new MenuItem("레드와인", 60000, MenuCategory.BEVERAGE));
        menuItems.add(new MenuItem("샴폐인", 25000, MenuCategory.BEVERAGE));
        return menuItems;
    }
}
