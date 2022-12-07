package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;
    private int count;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현
    public static List<Coin> generateCoins(List<Integer> randomElements) {
        List<Coin> coins = new ArrayList<>();
        int idx = -1;
        for (Coin coin : values()) {
            idx++;
            coin.count = randomElements.get(idx);
            coins.add(coin);
        }
        return coins;
    }



    public int getAmount() {
        return amount;
    }

    public int getCount() {
        return count;
    }
}
