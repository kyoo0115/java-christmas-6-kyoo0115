package christmas.view;

import christmas.model.entity.EventBadge;
import christmas.model.entity.Order;
import christmas.model.service.GiftService;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    public void displayOrderItems(Order order) {
        System.out.println("<주문 메뉴>");
        order.getItems().forEach((menuItem, quantity) ->
                System.out.printf("%s %d개\n", menuItem.name(), quantity));
        System.out.println();
    }

    public void displayBenefitsForTheDay(int date){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", date);
    }

    public void displayTotalPriceBeforeDiscount(Order order) {
        System.out.printf("<할인 전 총주문 금액>\n%,d원\n\n", order.calculateTotalPrice());
    }

    public void displayTotalPriceAfterDiscount(long discountedTotalPrice) {
        System.out.printf("<할인 후 예상 결제 금액>\n%,d원\n\n", discountedTotalPrice);
    }

    public void displayEventBadge(EventBadge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.toString());
    }

    public void displayDiscountDetails(Order order) {
        System.out.println("<혜택 내역>");
        if (order.getDiscountDetails().isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }

        for (Map.Entry<String, Long> detail : order.getDiscountDetails().entrySet()) {
            System.out.printf("%s: -%,d원\n", detail.getKey(), detail.getValue());
        }
        System.out.println();
    }

    public void displayTotalBenefitAmount(Order order) {
        long totalBenefits = order.calculateTotalBenefitAmount();
        if (totalBenefits <= 0) {
            System.out.println("<총혜택 금액>\n0원\n");
            return;
        }

        System.out.printf("<총혜택 금액>\n-%,d원\n\n", totalBenefits);
    }

    public void displayGiftItem(Order order) {
        Optional<String> giftItem = new GiftService().getGiftItem(order);
        System.out.println("<증정 메뉴>");
        if (giftItem.isPresent()) {
            System.out.println(giftItem.get() + " 1개");
            System.out.println();
            return;
        }
        System.out.println("없음");
        System.out.println();
    }
}