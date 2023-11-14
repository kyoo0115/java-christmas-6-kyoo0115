package christmas;

import christmas.model.validator.DateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {

    private DateValidator dateValidator;

    @BeforeEach
    void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    @DisplayName("유효한 날짜 입력은 검증을 통과해야 함")
    void whenValidDateInput_thenSuccess() {
        assertEquals(15, dateValidator.validate("15"));
    }

    @Test
    @DisplayName("유효하지 않은 날짜 형식은 예외를 발생시켜야 함")
    void whenInvalidDateFormat_thenException() {
        assertThrows(IllegalArgumentException.class, () -> {
            dateValidator.validate("invalid");
        });
    }

    @Test
    @DisplayName("범위를 벗어난 날짜는 예외를 발생시켜야 함")
    void whenDateOutOfRange_thenException() {
        assertThrows(IllegalArgumentException.class, () -> {
            dateValidator.validate("32");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            dateValidator.validate("0");
        });
    }

    @Test
    @DisplayName("음수 날짜는 예외를 발생시켜야 함")
    void whenNegativeDate_thenException() {
        assertThrows(IllegalArgumentException.class, () -> {
            dateValidator.validate("-1");
        });
    }
}
