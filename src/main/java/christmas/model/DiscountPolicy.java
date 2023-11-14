package christmas.model;

import christmas.utils.DiscountCalculator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.Map;

public class DiscountPolicy {
    private static final int CHRISTMAS_DISCOUNT_START = 1000;
    private static final int CHRISTMAS_DISCOUNT_INCREMENT = 100;
    private static final LocalDate CHRISTMAS_DISCOUNT_BEGIN = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DISCOUNT_END = LocalDate.of(2023, 12, 25);
    private static final int WEEKDAY_DESSERT_DISCOUNT = 2023;
    private static final int WEEKEND_MAIN_DISCOUNT = 2023;
    public static final int SPECIAL_DISCOUNT = 1000;
    private static final EnumSet<DayOfWeek> WEEKDAYS = EnumSet.of(
            DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
    private static final EnumSet<DayOfWeek> WEEKENDS = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    public long calculateChristmasDiscount(LocalDate date) {
        return DiscountCalculator.calculateChristmasDiscount(date, CHRISTMAS_DISCOUNT_BEGIN, CHRISTMAS_DISCOUNT_END, CHRISTMAS_DISCOUNT_START, CHRISTMAS_DISCOUNT_INCREMENT);
    }

    public long calculateWeekdayDessertDiscount(int dessertCount, LocalDate date) {
        return DiscountCalculator.calculateWeekdayDessertDiscount(dessertCount, date, WEEKDAY_DESSERT_DISCOUNT, WEEKDAYS);
    }

    public long calculateWeekendMainDiscount(int mainCount, LocalDate date) {
        return DiscountCalculator.calculateWeekendMainDiscount(mainCount, date, WEEKEND_MAIN_DISCOUNT, WEEKENDS);
    }
}
