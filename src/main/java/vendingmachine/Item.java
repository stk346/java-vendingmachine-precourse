package vendingmachine;

import java.util.regex.PatternSyntaxException;

public class Item {

    private final String name;
    private final int price;
    private int count;

    public Item(String itemInfo) throws IllegalArgumentException {
        String[] info = generateItem(itemInfo);
        if (info.length != 3) {
            throw new IllegalArgumentException("올바른 상품 정보를 입력해주세요.");
        }
        try {
            this.name = info[0];
            this.price = Integer.parseInt(info[1]);
            this.count = Integer.parseInt(info[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("가격, 수량은 숫자만 가능합니다.");
        }
    }

    private String[] generateItem(String itemInfo) throws IllegalArgumentException {
        if (!itemInfo.contains("[") && !itemInfo.contains("]")) {
            throw new IllegalArgumentException("개별 삼품은 대괄호([])로 묶어주세요.");
        }
        try {
            itemInfo = itemInfo.replace("[", "");
            itemInfo = itemInfo.replace("]", "");
            return itemInfo.split(",");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("상품 정보는 쉼표(,)로 구분해주세요.");
        }
    }

    public void buy() throws IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException("상품의 재고가 없습니다.");
        }
        count--;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name != null ? name.equals(item.name) : item.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
