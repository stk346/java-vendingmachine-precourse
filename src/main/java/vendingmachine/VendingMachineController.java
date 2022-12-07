package vendingmachine;

import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;

public class VendingMachineController {

    public void run() {
        int inputOfVendingMachineMoney = InputView.showEnterMachineMoneyMessageAndGetIt();
        RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator(inputOfVendingMachineMoney);
        List<Integer> randomElements = randomCoinGenerator.generateRandomElements();
        List<Coin> coins = Coin.generateCoins(randomElements);
        VendingMachine vendingMachine = new VendingMachine(coins);

        OutputView.showCoins(coins);

        String[] itemInfo = InputView.showEnterItemInfoMessageAndGetIt();
        for (String info : itemInfo) {
            vendingMachine.addItem(new Item(info));
        }

        int inputMoney = InputView.showEnterMoneyMessageAndGetIt();
        vendingMachine.addMoney(inputMoney);

        OutputView.showMoneyOfInserted(vendingMachine);
        String inputItemName = InputView.showEnterItemNameToBuyAndGetIt();
        vendingMachine.buy(inputItemName);

        if (vendingMachine.isRemainMoneySmallThanMinimumItemPrice()) {

        }
    }
}
