package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RandomCoinGenerator {
    private int money;
    private List<Integer> subtractionCounts = new ArrayList<>();

    public RandomCoinGenerator(int money) throws IllegalArgumentException {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 양수만 가능합니다.");
        }
        if (money % 10 > 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원으로 나누어 떨어져야 합니다.");
        }
        this.money = money;
    }

//    public List<Integer> generateRandomElements() {
//        int subtractionFiveHundredsForRandomly = subtractionRandomlyTo(money, 500);
//        int subtractionOneHundredsForRandomly = subtractionRandomlyTo(subtractionFiveHundredsForRandomly, 100);
//        int subtractionFiftyForRandomly = subtractionRandomlyTo(subtractionOneHundredsForRandomly, 50);
//        int restOfCount = subtractionFiftyForRandomly / 10;
//        subtractionCounts.add(restOfCount);
//        return subtractionCounts;
//    }
//
//    private int subtractionRandomlyTo(int money, int subtractionElement) {
//        int subtractionElementForRandom = money / subtractionElement;
//        List<Integer> numbers = new ArrayList<>();
//        for (int i = 0; i < subtractionElementForRandom; i++) {
//            numbers.add(i);
//        }
//        try {
//            int randomlyPickedNumber = Randoms.pickNumberInList(numbers);
//            subtractionCounts.add(randomlyPickedNumber);
//            return money - randomlyPickedNumber * subtractionElement;
//        } catch (IllegalArgumentException e) {
//            subtractionCounts.add(0);
//            return money;
//        }
//    }

    public List<Integer> generateRandomElements() {
        List<Integer> numbersForRandomSelect = Arrays.asList(500, 100, 50, 10);
        HashMap<Integer, Integer> randomlySelectedNumbers = getRandomNumbers(numbersForRandomSelect);
        for (int coinUnit : numbersForRandomSelect) {
            subtractionCounts.add(randomlySelectedNumbers.getOrDefault(coinUnit, 0));
        }
        return subtractionCounts;
    }

    private HashMap<Integer, Integer> getRandomNumbers(List<Integer> numbersForRandomSelect) {
        HashMap<Integer, Integer> randomlySelectedNumbers = new HashMap<>();
        int sum = 0;
        while (sum != money) {
            int randomlySelectedNumber = Randoms.pickNumberInList(numbersForRandomSelect);
            sum += randomlySelectedNumber;
            if (sum > money) {
                sum -= randomlySelectedNumber;
                continue;
            }
            randomlySelectedNumbers.put(randomlySelectedNumber, randomlySelectedNumbers.getOrDefault(randomlySelectedNumber, 0) + 1);
        }
        return randomlySelectedNumbers;
    }
}
