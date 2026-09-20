package christmas.view;

import christmas.model.entity.EventBadge;
import christmas.model.entity.Order;
import christmas.model.service.GiftService;

import java.util.Map;

public class OutputView {

    private final GiftService giftService;

    public OutputView(GiftService giftService) {
        this.giftService = giftService;
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public void displayBenefitsPreview(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", date);
        System.out.println();
    }

    public void displayOrderItems(Order order) {
        System.out.println("<주문 메뉴>");
        order.getItems().forEach((item, qty) ->
                System.out.printf("%s %d개%n", item.name(), qty));
        System.out.println();
    }

    public void displayTotalPriceBeforeDiscount(Order order) {
        System.out.printf("<할인 전 총주문 금액>%n%,d원%n%n", order.calculateTotalPrice());
    }

    public void displayGiftItem(Order order) {
        System.out.println("<증정 메뉴>");
        String gift = giftService.getGiftItem(order)
                .map(name -> name + " 1개")
                .orElse("없음");
        System.out.println(gift);
        System.out.println();
    }

    public void displayDiscountDetails(Order order) {
        System.out.println("<혜택 내역>");
        Map<String, Long> details = order.getDiscountDetails();
        if (details.isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        details.forEach((label, amount) ->
                System.out.printf("%s: -%,d원%n", label, amount));
        System.out.println();
    }

    public void displayTotalBenefitAmount(Order order) {
        long total = order.calculateTotalBenefitAmount();
        if (total <= 0) {
            System.out.printf("<총혜택 금액>%n0원%n%n");
            return;
        }
        System.out.printf("<총혜택 금액>%n-%,d원%n%n", total);
    }

    public void displayTotalPriceAfterDiscount(long discountedPrice) {
        System.out.printf("<할인 후 예상 결제 금액>%n%,d원%n%n", discountedPrice);
    }

    public void displayEventBadge(EventBadge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
