package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private final List<Item> items = new ArrayList<>();
    private final List<Coin> heldCoin;

    private int inputAmount;

    public VendingMachine(List<Coin> coins) {
        this.heldCoin = coins;
    }

    public void buy(String itemName) {
        Item item = getItem(itemName);
        int itemPrice = item.getPrice();
        inputAmount = inputAmount - itemPrice;
        item.buy();
    }

    private Item getItem(String itemName) throws IllegalArgumentException {
        for (Item item : items) {
            if (item.equals(itemName)) {
                return item;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 상품입니다.");
    }

    

    public boolean isRemainMoneySmallThanMinimumItemPrice() {
        int minimumPrice = getMinimumItemPrice();
        if (inputAmount < minimumPrice) {
            return true;
        }
        return false;
    }

    private int getMinimumItemPrice() {
        int minimumPrice = 0;
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

    public void addMoney(int money) {
        this.inputAmount = money;
    }

    public int getInputAmount() {
        return inputAmount;
    }
}
