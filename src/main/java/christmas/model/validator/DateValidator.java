package christmas.model.validator;

import christmas.utils.Constants;
import christmas.view.ExceptionView;

public class DateValidator implements Validator<Integer>{
    @Override
    public Integer validate(String input) {
        try {
            int date = Integer.parseInt(input);
            if (date < Constants.START_OF_DECEMBER || date > Constants.END_OF_DECEMBER) {
                throw new IllegalArgumentException(ExceptionView.INVALID_DATE.getMessage());
            }
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionView.INVALID_DATE.getMessage());
        }
    }
}
