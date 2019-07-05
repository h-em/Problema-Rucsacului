import java.util.Comparator;

public class ItemComparatorForUselessItemOrderedByWeight implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        double profit1 = first.getValue() / first.getWeight();
        double profit2 = second.getValue() / second.getWeight();

        if( Double.compare(profit1,profit2) == 0){
            return Double.compare(second.getWeight(), first.getWeight());
        }
        return Double.compare(profit1,profit2);
    }
}
