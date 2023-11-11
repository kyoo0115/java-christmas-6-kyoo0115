package christmas.model.validator;

public class DateValidator implements Validator<Integer>{
    @Override
    public Integer validate(String input) {
        try {
            int date = Integer.parseInt(input);
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해 주세요.");
        }
    }
}
