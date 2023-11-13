package christmas.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;

public class DiscountCalculator {
    public static long calculateChristmasDiscount(LocalDate date, LocalDate start, LocalDate end, int startAmount, int increment) {
        if (date.isBefore(start) || date.isAfter(end)) {
            return 0;
        }
        long daysBetween = ChronoUnit.DAYS.between(start, date);
        return startAmount + daysBetween * increment;
    }

    public static long calculateWeekdayDessertDiscount(int count, LocalDate date, int discountPerItem, EnumSet<DayOfWeek> weekdays) {
        if (weekdays.contains(date.getDayOfWeek())) {
            return (long) count * discountPerItem;
        }
        return 0;
    }

    public static long calculateWeekendMainDiscount(int count, LocalDate date, int discountPerItem, EnumSet<DayOfWeek> weekends) {
        if (weekends.contains(date.getDayOfWeek())) {
            return (long) count * discountPerItem;
        }
        return 0;
    }
}
