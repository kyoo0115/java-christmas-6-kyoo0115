package christmas.controller;

import christmas.model.validator.DateValidator;
import christmas.model.validator.OrderListValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Set;

public class InputController {

    private final DateValidator dateValidator;
    private final OrderListValidator orderListValidator;
    private final OutputView outputView;

    public InputController(Set<String> validMenuNames, OutputView outputView) {
        this.dateValidator = new DateValidator();
        this.orderListValidator = new OrderListValidator(validMenuNames);
        this.outputView = outputView;
    }

    public int readAndValidateVisitDate() {
        while (true) {
            try {
                return dateValidator.validate(InputView.readVisitDate());
            } catch (IllegalArgumentException e) {
                outputView.displayError(e.getMessage());
            }
        }
    }

    public String readAndValidateOrderList() {
        while (true) {
            try {
                return orderListValidator.validate(InputView.readOrderList());
            } catch (IllegalArgumentException e) {
                outputView.displayError(e.getMessage());
            }
        }
    }
}
