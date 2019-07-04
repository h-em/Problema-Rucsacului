import java.util.Comparator;

public class ItemComparatorForUselessItems implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        double profit1 = first.getValue() / first.getWeight();
        double profit2 = second.getValue() / second.getWeight();

        return Double.compare(profit1,profit2);
    }
}
