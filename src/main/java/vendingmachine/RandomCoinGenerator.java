package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class RandomCoinGenerator {
    private int money;
    private List<Integer> subtractionCounts = new ArrayList<>();

    public RandomCoinGenerator(int money) {
        this.money = money;
    }

    public List<Integer> generateRandomElements() {
        int subtractionFiveHundredsForRandomly = subtractionRandomlyTo(money, 500);
        int subtractionOneHundredsForRandomly = subtractionRandomlyTo(subtractionFiveHundredsForRandomly, 100);
        int subtractionFiftyForRandomly = subtractionRandomlyTo(subtractionOneHundredsForRandomly, 50);
        int restOfCount = subtractionFiftyForRandomly / 10;
        subtractionCounts.add(restOfCount);
        return subtractionCounts;
    }

    private int subtractionRandomlyTo(int money, int subtractionElement) {
        int subtractionElementForRandom = money / subtractionElement;
        int randomlyPickedNumber = Randoms.pickNumberInRange(0, subtractionElementForRandom);
        subtractionCounts.add(randomlyPickedNumber);
        return money - randomlyPickedNumber * subtractionElement;
    }
}
