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


    public boolean addItemOrFractionOfAnItem(Item item) {
        double availableCapacity = getAvailableCapacity();

        if (availableCapacity == 0) return false;
        if (availableCapacity >= item.getWeight()) {
            items.add(item);
            return true;
        } else {
            double newFractionValue = availableCapacity * item.getValue() / item.getWeight();
            Item fractionItem = new Item(item.getName(), newFractionValue, availableCapacity);
            items.add(fractionItem);
            return true;
        }
    }

    public void addItemOrFractionOfAnItemOneByOne(Item itemToAdd) {
        double availableCapacity = getAvailableCapacity();
        boolean itemToAddIsMoreUselessThanTheMostUselessItem = false;
        if (availableCapacity == 0) {

            while (availableCapacity <= itemToAdd.getWeight()
                    && !itemToAddIsMoreUselessThanTheMostUselessItem) {

                Item theMostUselessItem = getTheMostUselessItem(items);
                double profitForTheMostUselessItem = theMostUselessItem.getValue() / theMostUselessItem.getWeight();
                double profitItem = itemToAdd.getValue() / itemToAdd.getWeight();

                if (profitItem > profitForTheMostUselessItem) {
                    items.remove(theMostUselessItem);

                } else if (profitItem == profitItem) {

                    if (itemToAdd.getWeight() < theMostUselessItem.getWeight()) {
                        items.remove(theMostUselessItem);
                    } else {
                        Item theHeaviestItem = getTheHeaviestItem(items, itemToAdd);
                        items.remove(theHeaviestItem);

                        if (theHeaviestItem == null) {
                            itemToAddIsMoreUselessThanTheMostUselessItem = true;
                        }
                    }
                }
                availableCapacity = getAvailableCapacity();
            }

            if (!itemToAddIsMoreUselessThanTheMostUselessItem) {
                items.add(itemToAdd);
            }

        } else if (availableCapacity >= itemToAdd.getWeight()) {
            items.add(itemToAdd);

        } else {
            double newFractionValue = availableCapacity * itemToAdd.getValue() / itemToAdd.getWeight();
            Item fractionItem = new Item(itemToAdd.getName(), newFractionValue, availableCapacity);
            items.add(fractionItem);
        }
    }

    public Item getTheMostUselessItem(List<Item> itemsList) {
        ItemComparatorForUselessItemOrderedByWeight comparator = new ItemComparatorForUselessItemOrderedByWeight();
        Set<Item> uselesItemsSet = new TreeSet<>(comparator);
        uselesItemsSet.addAll(itemsList);
        Queue<Item> uselesItemsQueue = new LinkedList<>(uselesItemsSet);

        return uselesItemsQueue.poll();
    }

    public Item getTheHeaviestItem(List<Item> itemsList, Item itemToCompare) {
        ItemComparatorForUselessItemOrderedByWeight comparator = new ItemComparatorForUselessItemOrderedByWeight();
        Set<Item> uselesItemsSet = new TreeSet<>(comparator);
        uselesItemsSet.addAll(itemsList);

        double maxWeight = itemToCompare.getWeight();
        double profitForItemToCompare = itemToCompare.getValue() / itemToCompare.getWeight();

        Item theHeaviestItem = null;
        Iterator<Item> setIterator = uselesItemsSet.iterator();
        //itereaza doar pana la un profit <= cu itemul pe care vreau sa-l adaug
        while (setIterator.hasNext()
                && setIterator.next().getValue() / setIterator.next().getWeight() <= profitForItemToCompare) {
            Item itemFormSet = setIterator.next();
            if (maxWeight < itemFormSet.getWeight()) {
                maxWeight = itemFormSet.getWeight();
                theHeaviestItem = itemFormSet;
            }
        }

        return theHeaviestItem;
    }


    @Override
    public String toString() {
        return "Backpack{" +
                ", currentCapacity=" + capacity +
                ", items=" + items +
                '}';
    }
}
