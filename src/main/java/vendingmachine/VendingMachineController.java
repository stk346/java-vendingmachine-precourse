package vendingmachine;

import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;

public class VendingMachineController {

    public void run() {
        VendingMachine vendingMachine = getVendingMachine();
        OutputView.showCoins(vendingMachine);

        addItems(vendingMachine);
        insertMoney(vendingMachine);

        keepPurchase(vendingMachine);
    }

    private VendingMachine getVendingMachine() {
        try {
            int inputOfVendingMachineMoney = InputView.showEnterMachineMoneyMessageAndGetIt();
            RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator(inputOfVendingMachineMoney);
            List<Integer> randomElements = randomCoinGenerator.generateRandomElements();
            List<Coin> coins = Coin.generateCoins(randomElements);
            VendingMachine vendingMachine = new VendingMachine(coins);
            return vendingMachine;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getVendingMachine();
        }
    }

    private void addItems(VendingMachine vendingMachine) {
        try {
            String[] itemInfo = InputView.showEnterItemInfoMessageAndGetIt();
            for (String info : itemInfo) {
                vendingMachine.addItem(new Item(info));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            addItems(vendingMachine);
        }
    }

    private void insertMoney(VendingMachine vendingMachine) {
        try {
            int inputMoney = InputView.showEnterMoneyMessageAndGetIt();
            vendingMachine.addMoney(inputMoney);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            insertMoney(vendingMachine);
        }
    }

    private void keepPurchase(VendingMachine vendingMachine) {
        while (true) {
            OutputView.showMoneyOfInserted(vendingMachine);
            getInputAndBuy(vendingMachine);
            if (ifRemainMoneyShorOf(vendingMachine)) break;
        }
    }

    private void getInputAndBuy(VendingMachine vendingMachine) {
        try {
            String inputItemName = InputView.showEnterItemNameToBuyAndGetIt();
            vendingMachine.buy(inputItemName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getInputAndBuy(vendingMachine);
        }
    }

    private boolean ifRemainMoneyShorOf(VendingMachine vendingMachine) {
        if (vendingMachine.isRemainMoneySmallThanMinimumItemPrice()) {
            OutputView.showMoneyOfInserted(vendingMachine);
            List<Coin> givingBackCoins = vendingMachine.getGivingBackCoins(vendingMachine.getInputAmount());
            OutputView.showGivingBackCoins(givingBackCoins);
            return true;
        }
        return false;
    }
}
