package christmas.controller;

import christmas.model.validator.DateValidator;
import christmas.model.validator.OrderListValidator;
import christmas.view.InputView;

import java.util.Set;

public class InputController {

    private final DateValidator dateValidator;
    private final OrderListValidator orderListValidator;

    public InputController(Set<String> validMenuItems) {
        this.dateValidator = new DateValidator();
        this.orderListValidator = new OrderListValidator(validMenuItems);
    }
    public int readAndValidateVisitDate() {
        while (true) {
            try {
                String inputDate = InputView.readVisitDate();
                return dateValidator.validate(inputDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readAndValidateOrderList() {
        while (true) {
            try {
                String inputOrderList = InputView.readOrderList();
                return orderListValidator.validate(inputOrderList);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
