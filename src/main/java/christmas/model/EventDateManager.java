package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventDateManager {

    private static final int EVENT_YEAR = 2023;
    private static final Month EVENT_MONTH = Month.DECEMBER;

    // All Sundays in December 2023, plus Christmas Eve and New Year's Eve
    private static final Set<LocalDate> SPECIAL_OFFER_DATES = computeSpecialOfferDates();

    private static Set<LocalDate> computeSpecialOfferDates() {
        LocalDate firstSunday = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 1)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Set<LocalDate> sundays = IntStream.iterate(firstSunday.getDayOfMonth(), d -> d <= 31, d -> d + 7)
                .mapToObj(d -> LocalDate.of(EVENT_YEAR, EVENT_MONTH, d))
                .collect(Collectors.toSet());

        sundays.add(LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25)); // Christmas
        sundays.add(LocalDate.of(EVENT_YEAR, EVENT_MONTH, 31)); // New Year's Eve
        return Set.copyOf(sundays);
    }

    public boolean isSpecialOfferDate(LocalDate date) {
        return SPECIAL_OFFER_DATES.contains(date);
    }
}
