package christmas;

import christmas.discount.DiscountPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

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
        long expectedDiscount = 1000 + 9 * 100; // 9 days after December 1
        assertEquals(expectedDiscount, discountPolicy.calculateChristmasDiscount(date));
    }

    @Test
    @DisplayName("크리스마스 할인은 지정된 기간 밖에서는 적용되지 않아야 함")
    void whenDateOutsideChristmasPeriod_thenNoChristmasDiscount() {
        LocalDate date = LocalDate.of(2023, 11, 30);
        assertEquals(0, discountPolicy.calculateChristmasDiscount(date));
    }

    @Test
    @DisplayName("평일에는 디저트 할인이 적용되어야 함")
    void whenWeekdayAndDessert_thenApplyWeekdayDessertDiscount() {
        LocalDate weekday = LocalDate.of(2023, 12, 4);
        int dessertCount = 2;
        long expectedDiscount = 2 * 2023;
        assertEquals(expectedDiscount, discountPolicy.calculateWeekdayDessertDiscount(dessertCount, weekday));
    }

    @Test
    @DisplayName("주말에는 디저트 할인이 적용되지 않아야 함")
    void whenWeekendAndDessert_thenNoWeekdayDessertDiscount() {
        LocalDate weekend = LocalDate.of(2023, 12, 2);
        int dessertCount = 2;
        assertEquals(0, discountPolicy.calculateWeekdayDessertDiscount(dessertCount, weekend));
    }

    @Test
    @DisplayName("주말에는 메인 요리 할인이 적용되어야 함")
    void whenWeekendAndMain_thenApplyWeekendMainDiscount() {
        LocalDate weekend = LocalDate.of(2023, 12, 2);
        int mainCount = 3;
        long expectedDiscount = 3 * 2023;
        assertEquals(expectedDiscount, discountPolicy.calculateWeekendMainDiscount(mainCount, weekend));
    }

    @Test
    @DisplayName("평일에는 메인 요리 할인이 적용되지 않아야 함")
    void whenWeekdayAndMain_thenNoWeekendMainDiscount() {
        LocalDate weekday = LocalDate.of(2023, 12, 5);
        int mainCount = 3;
        assertEquals(0, discountPolicy.calculateWeekendMainDiscount(mainCount, weekday));
    }
}
