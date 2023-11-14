package christmas.view;

public enum ExceptionView {
    INVALID_MENU_ITEM("[ERROR] 메뉴판에 없는 메뉴를 입력하셨습니다. 다시 입력해 주세요."),
    INVALID_ORDER_FORMAT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_QUANTITY("[ERROR] 메뉴의 개수는 1 이상이어야 합니다. 다시 입력해 주세요."),
    DUPLICATE_MENU_ITEM("[ERROR] 중복 메뉴를 입력하셨습니다. 다시 입력해 주세요.");

    private final String message;
    ExceptionView(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
