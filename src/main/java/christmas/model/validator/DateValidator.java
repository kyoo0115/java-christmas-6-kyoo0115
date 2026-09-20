package christmas.model.validator;

import christmas.model.exception.ErrorMessage;

public class DateValidator implements Validator<Integer> {

    private static final int START_OF_DECEMBER = 1;
    private static final int END_OF_DECEMBER   = 31;

    @Override
    public Integer validate(String input) {
        try {
            int date = Integer.parseInt(input);
            if (date < START_OF_DECEMBER || date > END_OF_DECEMBER) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
            }
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
