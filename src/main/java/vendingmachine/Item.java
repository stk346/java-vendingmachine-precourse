package vendingmachine;

import java.util.regex.PatternSyntaxException;

public class Item {

    private final String name;
    private final int price;
    private int count;

    public Item(String itemInfo) throws IllegalArgumentException {
        String[] info = getInfo(itemInfo);
        try {
            this.name = info[0];
            this.price = validatePrice(info);
            this.count = validateCount(info);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 가격, 수량은 숫자만 가능합니다.");
        }
    }

    private String[] getInfo(String itemInfo) {
        String[] info = generateItem(itemInfo);
        if (info.length != 3) {
            throw new IllegalArgumentException("[ERROR] 올바른 상품 정보를 입력해주세요.");
        }
        return info;
    }

    private String[] generateItem(String itemInfo) throws IllegalArgumentException {
        if (!itemInfo.contains("[") && !itemInfo.contains("]")) {
            throw new IllegalArgumentException("[ERROR] 개별 삼품은 대괄호([])로 묶어주세요.");
        }
        try {
            itemInfo = itemInfo.replace("[", "");
            itemInfo = itemInfo.replace("]", "");
            return itemInfo.split(",");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("[ERROR] 상품 정보는 쉼표(,)로 구분해주세요.");
        }
    }

    private int validatePrice(String[] info) {
        int price = Integer.parseInt(info[1]);
        if (price < 100 && price % 10 > 0) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 100원 이상이어야 하고 10원으로 나누어 떨어져야 합니다.");
        }
        return price;
    }

    private int validateCount(String[] info) {
        int count = Integer.parseInt(info[2]);
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 재고는 0개 이상이어야 합니다.");
        }
        return count;
    }

    public void buy() throws IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException("[ERROR] 상품의 재고가 없습니다.");
        }
        count--;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this.name.equals(o)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
