package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public enum Prompt {
        WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
        VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        ORDER_LIST("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        Prompt(String message) {
            this.message = message;
        }

        public void display() {
            System.out.println(message);
        }
    }

    public static String readVisitDate() {
        Prompt.VISIT_DATE.display();
        return Console.readLine();
    }

    public static String readOrderList() {
        Prompt.ORDER_LIST.display();
        return Console.readLine();
    }
}
