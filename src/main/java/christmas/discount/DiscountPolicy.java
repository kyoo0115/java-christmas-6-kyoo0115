package christmas.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;

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
        if (date.isBefore(CHRISTMAS_DISCOUNT_BEGIN) || date.isAfter(CHRISTMAS_DISCOUNT_END)) {
            return 0;
        }
        long daysBetween = ChronoUnit.DAYS.between(CHRISTMAS_DISCOUNT_BEGIN, date);
        return CHRISTMAS_DISCOUNT_START + daysBetween * CHRISTMAS_DISCOUNT_INCREMENT;
    }

    public long calculateWeekdayDessertDiscount(int dessertCount, LocalDate date) {
        if (WEEKDAYS.contains(date.getDayOfWeek())) {
            return (long) dessertCount * WEEKDAY_DESSERT_DISCOUNT;
        }
        return 0;
    }

    public long calculateWeekendMainDiscount(int mainCount, LocalDate date) {
        if (WEEKENDS.contains(date.getDayOfWeek())) {
            return (long) mainCount * WEEKEND_MAIN_DISCOUNT;
        }
        return 0;
    }
}
