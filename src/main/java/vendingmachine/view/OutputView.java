package vendingmachine.view;

import vendingmachine.Coin;
import vendingmachine.VendingMachine;

import java.util.List;

public class OutputView {
    public static void showCoins(List<Coin> coins) {
        System.out.println("\n자판기가 보유한 동전");
        for (Coin coin : coins) {
            System.out.println(coin.getAmount() + "원 - " + coin.getCount() + "개");
        }
    }

    public static void showMoneyOfInserted(VendingMachine vendingMachine) {
        System.out.println("투입 금액: " + vendingMachine.getInputAmount());
    }
}
