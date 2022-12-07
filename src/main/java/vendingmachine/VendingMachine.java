package vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachine {

    private final List<Item> items = new ArrayList<>();
    private final List<Coin> heldCoin;
    private int inputAmount;

    public VendingMachine(List<Coin> coins) {
        this.heldCoin = coins;
    }

    public void buy(String itemName) throws IllegalArgumentException {
        Item item = getItem(itemName);
        int itemPrice = item.getPrice();
        if (inputAmount < itemPrice) {
            throw new IllegalArgumentException("[ERROR] 남은 금액보다 상품 금액이 큽니다.");
        }
        inputAmount = inputAmount - itemPrice;
        item.buy();
    }

    private Item getItem(String itemName) throws IllegalArgumentException {
        for (Item item : items) {
            if (item.equals(itemName)) {
                return item;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
    }

    public List<Coin> getGivingBackCoins(int money) {
        List<Coin> givingBackCoins = new ArrayList<>();
        for (Coin coin : heldCoin) {
            money = addGivingCoinsAndCalculateRemainMoney(money, givingBackCoins, coin);
        }
        return givingBackCoins;
    }

    private int addGivingCoinsAndCalculateRemainMoney(int money, List<Coin> givingBackCoins, Coin coin) {
        int coinAmount = coin.getAmount();
        int subtractUnit = money / coinAmount;
        int possessedCoinCount = coin.getCount();
        if (subtractUnit > 0 && possessedCoinCount > 0) {
            if (subtractUnit > possessedCoinCount) {
                subtractUnit = possessedCoinCount;
                int remainMoney = money - coinAmount * subtractUnit;
                money = remainMoney;
                Coin givingBackCoin = Coin.getCoin(coin.getAmount());
                givingBackCoin.addCount(subtractUnit);
                givingBackCoins.add(givingBackCoin);
                coin.subtractCount(subtractUnit);
            }
        }
        return money;
    }

    public boolean isRemainMoneySmallThanMinimumItemPrice() {
        int minimumPrice = getMinimumItemPrice();
        if (inputAmount < minimumPrice) {
            return true;
        }
        return false;
    }

    private int getMinimumItemPrice() {
        int minimumPrice = Integer.MAX_VALUE;
        for (Item item : items) {
            int itemPrice = item.getPrice();
            if (minimumPrice > itemPrice) {
                minimumPrice = itemPrice;
            }
        }
        return minimumPrice;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addMoney(int money) throws IllegalArgumentException {
        if (money < 10) {
            throw new IllegalArgumentException("[ERROR] 10원 이상의 금액을 넣어주세요.");
        }
        if (money % 10 > 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원으로 나누어 떨어져야 합니다.");
        }
        this.inputAmount = money;
    }

    public int getInputAmount() {
        return inputAmount;
    }

    public List<Coin> getPossessedCoins() {
        return new ArrayList(heldCoin);
    }
}
