package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;


public class InputView {
    public static int showEnterMachineMoneyMessageAndGetIt() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        try {
            String stringUserInput = Console.readLine();
            return Integer.parseInt(stringUserInput);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자만 입력해주세요.");
            return showEnterMachineMoneyMessageAndGetIt();
        }
    }

    public static String[] showEnterItemInfoMessageAndGetIt() {
        System.out.println("상품명과 가격, 수량을 입력해주세요.");
        String userInput = Console.readLine();
        try {
            return userInput.split(";");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 상품은 세미콜론(;)으로 구분해주세요.");
            return showEnterItemInfoMessageAndGetIt();
        }
    }

    public static int showEnterMoneyMessageAndGetIt() {
        System.out.println("투입 금액을 입력해주세요.");
        String userInput = Console.readLine();
        try {
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자만 가능합니다.");
            return showEnterMoneyMessageAndGetIt();
        }
    }

    public static String showEnterItemNameToBuyAndGetIt() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        return Console.readLine();
    }
}
