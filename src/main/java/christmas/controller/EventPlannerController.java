package christmas.controller;

import christmas.discount.DiscountPolicy;
import christmas.model.*;
import christmas.model.entity.EventBadge;
import christmas.model.entity.MenuItem;
import christmas.model.entity.Order;
import christmas.model.service.BadgeService;
import christmas.model.service.GiftService;
import christmas.model.service.OrderService;
import christmas.discount.DiscountCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.utils.MenuLoader;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;

public class EventPlannerController {

    private final InputController inputController;
    private final OutputView outputView;
    private final BadgeService badgeService;
    private final DiscountPolicy discountPolicy;
    private final EventDateManager eventDateManager;
    private final GiftService giftService;
    private final OrderService orderService;
    private Order order;
    private LocalDate visitDate;

    public EventPlannerController() {
        Set<MenuItem> menuItems = MenuLoader.loadMenuItems();
        Set<String> validMenuNames = menuItems.stream()
                .map(MenuItem::name)
                .collect(Collectors.toSet());
        this.orderService = new OrderService(menuItems);
        this.inputController = new InputController(validMenuNames);
        this.outputView = new OutputView();
        this.badgeService = new BadgeService();
        this.discountPolicy = new DiscountPolicy();
        this.giftService = new GiftService();
        this.eventDateManager = new EventDateManager();
        runEventPlanner();
    }

    public void runEventPlanner() {
        welcomeGuests();
        collectVisitDate();
        collectOrderList();
        calculateDiscounts();
        printReceipt(order);
    }

    private void welcomeGuests() {
        InputView.Prompt.WELCOME_MESSAGE.display();
    }

    private void collectVisitDate() {
        int dayOfMonth = inputController.readAndValidateVisitDate();
        this.visitDate = LocalDate.of(2023, Month.DECEMBER, dayOfMonth);
        outputView.displayBenefitsForTheDay(dayOfMonth);
    }

    private void collectOrderList() {
        String inputOrderList = inputController.readAndValidateOrderList();
        this.order = orderService.createOrderFromInput(inputOrderList);
    }

    public void calculateDiscounts() {
        DiscountCalculator calculator = new DiscountCalculator(discountPolicy, eventDateManager);
        this.order = calculator.calculateDiscounts(order, visitDate);
    }

    private void printReceipt(Order order) {
        giftService.processGiftEligibility(order);
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