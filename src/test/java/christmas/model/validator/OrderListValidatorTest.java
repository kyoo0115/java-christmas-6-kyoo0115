package christmas.model.validator;

import christmas.model.validator.OrderListValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class OrderListValidatorTest {

    private OrderListValidator orderListValidator;

    @BeforeEach
    void setUp() {
        Set<String> sampleMenuItems = Set.of("해산물파스타", "레드와인", "초코케이크", "타파스", "제로콜라");
        orderListValidator = new OrderListValidator(sampleMenuItems);
    }

    @Test
    @DisplayName("Valid order should pass validation")
    void whenValidOrderInput_thenSuccess() {
        String input = "해산물파스타-2,레드와인-1";
        assertDoesNotThrow(() -> orderListValidator.validate(input));
    }

    @Test
    @DisplayName("Invalid order format should throw exception")
    void whenInvalidOrderFormat_thenException() {
        String input = "해산물파스타/2";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderListValidator.validate(input);
        });
        assertTrue(exception.getMessage().contains("[ERROR] 유효하지 않은 주문입니다."));
    }

    @Test
    @DisplayName("Order with non-existing menu item should throw exception")
    void whenOrderWithNonExistingMenuItem_thenException() {
        String input = "해산물파스타-2,없는메뉴-1";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderListValidator.validate(input);
        });
        assertTrue(exception.getMessage().contains("[ERROR] 메뉴판에 없는 메뉴를 입력하셨습니다."));
    }

    @Test
    @DisplayName("Order with duplicate menu items should throw exception")
    void whenOrderWithDuplicateItems_thenException() {
        String input = "타파스-1,타파스-1";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderListValidator.validate(input);
        });
        assertTrue(exception.getMessage().contains("[ERROR] 중복 메뉴를 입력하셨습니다."));
    }

    @Test
    @DisplayName("Order with invalid quantity should throw exception")
    void whenOrderWithInvalidQuantity_thenException() {
        String input = "해산물파스타-0,레드와인-1";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderListValidator.validate(input);
        });
        assertTrue(exception.getMessage().contains("[ERROR] 메뉴의 개수는 1 이상이어야 합니다."));
    }
}
