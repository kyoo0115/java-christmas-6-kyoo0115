package christmas.model.discount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void setUp() {
        discountPolicy = new DiscountPolicy();
    }

    @Test
    @DisplayName("크리스마스 할인은 지정된 기간 동안만 적용되어야 함")
    void whenDateInChristmasPeriod_thenApplyChristmasDiscount() {
        LocalDate date = LocalDate.of(2023, 12, 10);
        long expected = 1_000 + 9 * 100L;
        assertEquals(expected, discountPolicy.calculateChristmasDiscount(date));
    }

    @Test
    @DisplayName("크리스마스 할인은 지정된 기간 밖에서는 적용되지 않아야 함")
    void whenDateOutsideChristmasPeriod_thenNoChristmasDiscount() {
        assertEquals(0, discountPolicy.calculateChristmasDiscount(LocalDate.of(2023, 11, 30)));
    }

    @Test
    @DisplayName("평일에는 디저트 할인이 적용되어야 함")
    void whenWeekdayAndDessert_thenApplyWeekdayDessertDiscount() {
        LocalDate weekday = LocalDate.of(2023, 12, 4); // Monday
        assertEquals(2 * 2_023L, discountPolicy.calculateWeekdayDessertDiscount(2, weekday));
    }

    @Test
    @DisplayName("주말에는 디저트 할인이 적용되지 않아야 함")
    void whenWeekendAndDessert_thenNoWeekdayDessertDiscount() {
        LocalDate weekend = LocalDate.of(2023, 12, 2); // Saturday
        assertEquals(0, discountPolicy.calculateWeekdayDessertDiscount(2, weekend));
    }

    @Test
    @DisplayName("주말에는 메인 요리 할인이 적용되어야 함")
    void whenWeekendAndMain_thenApplyWeekendMainDiscount() {
        LocalDate weekend = LocalDate.of(2023, 12, 2);
        assertEquals(3 * 2_023L, discountPolicy.calculateWeekendMainDiscount(3, weekend));
    }

    @Test
    @DisplayName("평일에는 메인 요리 할인이 적용되지 않아야 함")
    void whenWeekdayAndMain_thenNoWeekendMainDiscount() {
        LocalDate weekday = LocalDate.of(2023, 12, 5); // Tuesday
        assertEquals(0, discountPolicy.calculateWeekendMainDiscount(3, weekday));
    }
}
