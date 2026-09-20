package christmas.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderListValidatorTest {

    private static final String INVALID_ORDER_MSG = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private OrderListValidator orderListValidator;

    @BeforeEach
    void setUp() {
        orderListValidator = new OrderListValidator(
                Set.of("해산물파스타", "레드와인", "초코케이크", "타파스", "제로콜라"));
    }

    @Test
    @DisplayName("유효한 주문은 검증을 통과해야 함")
    void whenValidOrderInput_thenSuccess() {
        assertDoesNotThrow(() -> orderListValidator.validate("해산물파스타-2,레드와인-1"));
    }

    @Test
    @DisplayName("형식이 잘못된 주문은 예외를 발생시켜야 함")
    void whenInvalidOrderFormat_thenException() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> orderListValidator.validate("해산물파스타/2"));
        assertTrue(e.getMessage().contains("[ERROR] 유효하지 않은 주문입니다."));
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴는 예외를 발생시켜야 함")
    void whenOrderWithNonExistingMenuItem_thenException() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> orderListValidator.validate("해산물파스타-2,없는메뉴-1"));
        assertTrue(e.getMessage().contains("[ERROR] 유효하지 않은 주문입니다."));
    }

    @Test
    @DisplayName("중복 메뉴는 예외를 발생시켜야 함")
    void whenOrderWithDuplicateItems_thenException() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> orderListValidator.validate("타파스-1,타파스-1"));
        assertTrue(e.getMessage().contains("[ERROR] 유효하지 않은 주문입니다."));
    }

    @Test
    @DisplayName("수량이 1 미만인 주문은 예외를 발생시켜야 함")
    void whenOrderWithInvalidQuantity_thenException() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> orderListValidator.validate("해산물파스타-0,레드와인-1"));
        assertTrue(e.getMessage().contains("[ERROR] 유효하지 않은 주문입니다."));
    }
}
