import java.util.Objects;

public class Item implements Comparable<Item> {

    private String name;
    private double value;
    private double weight;

    public Item(String name, double value, double weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name +
                ": (value=" + value +
                ", weight=" + weight +
                ")\n";
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Item anotherItem) {
        double profit1 = value / weight;
        double profit2 = anotherItem.value / anotherItem.weight;
        return Double.compare(profit2, profit1);
    }
}
