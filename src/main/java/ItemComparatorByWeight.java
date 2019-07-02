import java.util.Comparator;

public class ItemComparatorByWeight implements Comparator<Item> {

    @Override
    public int compare(Item firstItem, Item secondItem) {
        return Double.compare(firstItem.getWeight(), secondItem.getWeight());
    }
}
