import java.util.*;

public class Backpack {

    private double capacity;
    private List<Item> items;

    public Backpack(double capacity) {
        this.capacity = capacity;
        items = new LinkedList<>();
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItemList(List<Item> items) {
        this.items = items;
    }


    public double getTotalWeightOfItems() {
        double totalCapacity = 0;
        for (Item item : items) {
            totalCapacity += item.getWeight();
        }
        return totalCapacity;
    }


    public double getTotalValueOfItem() {
        double totalValue = 0;
        for (Item item : items) {
            totalValue += item.getValue();
        }
        return totalValue;
    }

    public void add(Item item) {
        if (getAvailableCapacity() >= item.getWeight())
            items.add(item);
    }


    public double getAvailableCapacity() {
        return getCapacity() - getTotalWeightOfItems();
    }

    public int getNumberOfItems() {
        return items.size();
    }


    public void addItemOrFractionOfAnItem(Item item) {
        double availableCapacity = getAvailableCapacity();

        if (availableCapacity == 0) return;
        if (availableCapacity >= item.getWeight()) {
            items.add(item);
        } else {
            double newFractionValue = availableCapacity * item.getValue() / item.getWeight();
            Item fractionItem = new Item(item.getName(), newFractionValue, availableCapacity);
            items.add(fractionItem);
        }
    }

    public void addItemOrFractionOfAnItemOneByOne(Item item) {
        double availableCapacity = getAvailableCapacity();

        if (availableCapacity == 0) {//return;
            System.out.println("Backpack is full!");


            while(availableCapacity <= item.getWeight()){
                Item theMostUnlessItem = getTheMostUnlessItem(items);
                System.out.println("  ->>>removed " +theMostUnlessItem);
                items.remove(theMostUnlessItem);
                availableCapacity = getAvailableCapacity();
            }

            items.add(item);
        }
        else if (availableCapacity >= item.getWeight()) {
            System.out.println("item added: "+ item);
            items.add(item);
        } else {
            double newFractionValue = availableCapacity * item.getValue() / item.getWeight();
            Item fractionItem = new Item(item.getName(), newFractionValue, availableCapacity);
            System.out.println("Fraction of item added: "+ item);
            items.add(fractionItem);
        }
    }

    public Item getTheMostUnlessItem(List<Item> itemsList){
        ItemComparatorForUselessItems comparator = new ItemComparatorForUselessItems();
        Set<Item> uselesItemsSet = new TreeSet<>(comparator);
        uselesItemsSet.addAll(itemsList);
        Queue<Item> uselesItemsQueue= new LinkedList<>(uselesItemsSet);

        return uselesItemsQueue.poll();
    }


    @Override
    public String toString() {
        return "Backpack{" +
                ", currentCapacity=" + capacity +
                ", items=" + items +
                '}';
    }
}
