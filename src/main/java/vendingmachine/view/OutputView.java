package vendingmachine.view;

import vendingmachine.Coin;
import vendingmachine.VendingMachine;

import java.util.List;

public class OutputView {
    public static void showCoins(VendingMachine vendingMachine) {
        System.out.println("\n자판기가 보유한 동전");
        for (Coin coin : vendingMachine.getPossessedCoins()) {
            System.out.println(coin.getAmount() + "원 - " + coin.getCount() + "개");
        }
    }

    public static void showMoneyOfInserted(VendingMachine vendingMachine) {
        System.out.println("투입 금액: " + vendingMachine.getInputAmount() + "원");
    }

    public static void showGivingBackCoins(List<Coin> givingBackCoins) {
        System.out.println("잔돈");
        for (Coin coin : givingBackCoins) {
            System.out.println(coin.getAmount() + "원 - " + coin.getCount() + "개");
        }
    }
}
