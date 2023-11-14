package christmas.controller;

import christmas.model.*;
import christmas.model.service.BadgeService;
import christmas.model.service.GiftService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.utils.MenuLoader;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventPlannerController {

    private final InputController inputController;
    private final OutputView outputView;
    private final EventDateManager eventDateManager;
    private final Set<MenuItem> menuItems;
    private final GiftService giftService;
    private final BadgeService badgeService;
    private final DiscountPolicy discountPolicy;

    public EventPlannerController() {
        Set<String> validMenuNames = MenuLoader.loadMenuItems().stream()
                .map(MenuItem::name)
                .collect(Collectors.toSet());
        this.inputController = new InputController(validMenuNames);
        this.outputView = new OutputView();
        this.eventDateManager = new EventDateManager();
        this.menuItems = MenuLoader.loadMenuItems();
        this.giftService = new GiftService();
        this.badgeService = new BadgeService();
        this.discountPolicy = new DiscountPolicy();
        initiateEventPlanner();
    }

    public void initiateEventPlanner() {
        InputView.Prompt.WELCOME_MESSAGE.display();
        runEventPlanner();
    }

    private void runEventPlanner() {
        int dayOfMonth = inputController.readAndValidateVisitDate();
        LocalDate visitDate = LocalDate.of(2023, Month.DECEMBER, dayOfMonth);

        String inputOrderList = inputController.readAndValidateOrderList();
        Order order = createOrderFromInput(inputOrderList);
        outputView.displayBenefitsForTheDay(dayOfMonth);
        applyDiscounts(order, visitDate);
        printReceipt(order);
    }

    private Order createOrderFromInput(String validatedOrderList) {
        Order order = new Order();
        Map<String, MenuItem> menuItemMap = menuItems.stream()
                .collect(Collectors.toMap(MenuItem::getName, Function.identity()));

        String[] orderEntries = validatedOrderList.split(",");

        for (String validatedEntry : orderEntries) {
            String[] parts = validatedEntry.split("-");
            String itemName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            MenuItem item = menuItemMap.get(itemName);
            order.addItem(item, quantity);
        }

        return order;
    }

    public void applyDiscounts(Order order, LocalDate visitDate) {
        for (DiscountType discountType : DiscountType.values()) {
            long discountAmount = discountType.calculateDiscount(order, visitDate, discountPolicy, eventDateManager);
            if (discountAmount > 0) {
                order.addDiscountDetail(discountType.toString(), discountAmount);
            }
        }
    }

    private void printReceipt(Order order) {
        boolean isGiftEligible = giftService.checkGiftEligibility(order);
        order.setGiftEligibility(isGiftEligible);

        outputView.displayOrderItems(order);
        outputView.displayTotalPriceBeforeDiscount(order);
        outputView.displayGiftItem(order);
        outputView.displayDiscountDetails(order);
        outputView.displayTotalBenefitAmount(order);

        long totalPriceAfterDiscounts = order.calculateDiscountedTotalPrice();
        outputView.displayTotalPriceAfterDiscount(totalPriceAfterDiscounts);

        EventBadge badge = badgeService.determineBadge(order);
        outputView.displayEventBadge(badge);
    }

}
