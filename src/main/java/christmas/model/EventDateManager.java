package christmas.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EventDateManager {
    private final Set<LocalDate> specialOfferDates;

    public EventDateManager() {
        this.specialOfferDates = new HashSet<>();
        initializeSpecialOfferDates();
    }

    private void initializeSpecialOfferDates() {
        specialOfferDates.add(LocalDate.of(2023, 12, 3));
        specialOfferDates.add(LocalDate.of(2023, 12, 10));
        specialOfferDates.add(LocalDate.of(2023, 12, 17));
        specialOfferDates.add(LocalDate.of(2023, 12, 24));
        specialOfferDates.add(LocalDate.of(2023, 12, 25));
        specialOfferDates.add(LocalDate.of(2023, 12, 31));
    }

    public boolean isSpecialOfferDate(LocalDate date) {
        return specialOfferDates.contains(date);
    }
}
