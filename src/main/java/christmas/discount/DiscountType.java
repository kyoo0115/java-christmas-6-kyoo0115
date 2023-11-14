package christmas.discount;

import christmas.model.EventDateManager;
import christmas.model.entity.Order;
import christmas.discount.strategy.ChristmasDDayDiscount;
import christmas.discount.strategy.DiscountStrategy;
import christmas.discount.strategy.GiftEventDiscount;
import christmas.discount.strategy.SpecialDiscount;
import christmas.discount.strategy.WeekdayDiscount;
import christmas.discount.strategy.WeekendDiscount;
import java.time.LocalDate;

public enum DiscountType {
    CHRISTMAS_D_DAY(new ChristmasDDayDiscount(), "크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT(new WeekdayDiscount(), "평일 할인"),
    WEEKEND_DISCOUNT(new WeekendDiscount(), "주말 할인"),
    SPECIAL_DISCOUNT(new SpecialDiscount(), "특별 할인"),
    GIFT_EVENT(new GiftEventDiscount(), "증정 할인");

    private final DiscountStrategy strategy;
    private final String displayName;

    DiscountType(DiscountStrategy strategy, String displayName) {
        this.strategy = strategy;
        this.displayName = displayName;
    }

    public long calculateDiscount(Order order, LocalDate visitDate, DiscountPolicy discountPolicy, EventDateManager eventDateManager) {
        return strategy.calculateDiscount(order, visitDate, discountPolicy, eventDateManager);
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}

