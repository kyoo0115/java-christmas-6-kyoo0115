package christmas.model.discount;

import christmas.model.EventDateManager;
import christmas.model.discount.strategy.ChristmasDDayDiscount;
import christmas.model.discount.strategy.GiftEventDiscount;
import christmas.model.discount.strategy.SpecialDiscount;
import christmas.model.discount.strategy.WeekdayDiscount;
import christmas.model.discount.strategy.WeekendDiscount;
import christmas.model.discount.strategy.DiscountStrategy;

import java.util.EnumMap;
import java.util.Map;

public enum DiscountType {

    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIFT_EVENT("증정 할인");

    private final String displayName;

    DiscountType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Factory: build a DiscountCalculator with all strategies wired up.
     */
    public static DiscountCalculator createCalculator(DiscountPolicy policy, EventDateManager eventDateManager) {
        Map<DiscountType, DiscountStrategy> strategies = new EnumMap<>(DiscountType.class);
        strategies.put(CHRISTMAS_D_DAY, new ChristmasDDayDiscount(policy));
        strategies.put(WEEKDAY_DISCOUNT, new WeekdayDiscount(policy));
        strategies.put(WEEKEND_DISCOUNT, new WeekendDiscount(policy));
        strategies.put(SPECIAL_DISCOUNT, new SpecialDiscount(eventDateManager));
        strategies.put(GIFT_EVENT, new GiftEventDiscount());
        return new DiscountCalculator(strategies);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
