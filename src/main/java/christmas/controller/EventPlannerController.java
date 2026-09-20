package christmas.controller;

import christmas.model.EventDateManager;
import christmas.model.discount.DiscountCalculator;
import christmas.model.discount.DiscountPolicy;
import christmas.model.discount.DiscountType;
import christmas.model.entity.EventBadge;
import christmas.model.entity.Order;
import christmas.model.service.BadgeService;
import christmas.model.service.GiftService;
import christmas.model.service.MenuLoader;
import christmas.model.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Collectors;

public class EventPlannerController {

    private final InputController inputController;
    private final OutputView outputView;
    private final OrderService orderService;
    private final GiftService giftService;
    private final BadgeService badgeService;
    private final DiscountCalculator discountCalculator;

    public EventPlannerController() {
        var menuItems = MenuLoader.loadMenuItems();
        var menuNames = menuItems.stream()
                .map(item -> item.name())
                .collect(Collectors.toSet());
        var policy = new DiscountPolicy();
        var dateManager = new EventDateManager();

        this.giftService = new GiftService();
        this.outputView = new OutputView(giftService);
        this.inputController = new InputController(menuNames, outputView);
        this.orderService = new OrderService(menuItems);
        this.badgeService = new BadgeService();
        this.discountCalculator = DiscountType.createCalculator(policy, dateManager);
    }

    public void run() {
        InputView.Prompt.WELCOME_MESSAGE.display();

        int dayOfMonth = inputController.readAndValidateVisitDate();
        outputView.displayBenefitsPreview(dayOfMonth);

        String rawOrder = inputController.readAndValidateOrderList();
        Order order = orderService.createOrderFromInput(rawOrder);

        // Gift eligibility must be set before discount calculation
        // so GiftEventDiscount can read the pre-discount total correctly.
        giftService.processGiftEligibility(order);

        LocalDate visitDate = LocalDate.of(2023, Month.DECEMBER, dayOfMonth);
        discountCalculator.calculateDiscounts(order, visitDate);

        printReceipt(order);
    }

    private void printReceipt(Order order) {
        outputView.displayOrderItems(order);
        outputView.displayTotalPriceBeforeDiscount(order);
        outputView.displayGiftItem(order);
        outputView.displayDiscountDetails(order);
        outputView.displayTotalBenefitAmount(order);
        outputView.displayTotalPriceAfterDiscount(order.calculateDiscountedTotalPrice());
        EventBadge badge = badgeService.determineBadge(order);
        outputView.displayEventBadge(badge);
    }
}
