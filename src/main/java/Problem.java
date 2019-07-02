import java.util.*;

public class Problem {

    private Backpack backpack;
    private List<Item> availableItems;


    public Problem() {
        availableItems = new LinkedList<>();
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public List<Item> getItems() {
        return availableItems;
    }

    public void setItemsList(List<Item> items) {
        this.availableItems = items;
    }


    public void addAvailableItems(Item item) {
        availableItems.add(item);
    }


    public double getTotalWeightOfItemInBackpack() {
        return backpack.getTotalWeightOfItems();
    }

    public double getBackpackCapacity() {
        return backpack.getCapacity();
    }

    public double getBackpackValue() {
        return backpack.getTotalValueOfItem();
    }

    public int getNumbersOfItemsInBackpack() {
        return backpack.getNumberOfItems();
    }

    public Item getTheMostValuableItem() {
        double maxValue = 0;
        Item mostValuableItem = null;
        for (Item item : availableItems) {
            if (item.getValue() > maxValue) {
                maxValue = item.getValue();
                mostValuableItem = item;
            }
        }
        return mostValuableItem;
    }


    //////////lista de itemuri nesortate
    public void moveItemsToBackpack() {
        for (Item item : availableItems) {
            backpack.add(item);
        }
    }


    ////lista de item-uri sortate dupa profitul cel mai mare
    /// folosesc interfata Comparable<Item>
    public void moveProfitableItemsToBackpack() {
        ///introduc lista mea de item-uri in threeSet ca se fie sortate dupa ratia dintre value/weight!!
        //in functia compareTo() din Item este toata magia
        Set<Item> itemSet = new TreeSet<>(availableItems);
        for (Item item : itemSet) {
            backpack.add(item);
        }
    }

    ///// lista de itemuri cu greutatea cea mai mica
    //folosesc un comparator
    public void moveLightItemsToBackpack() {
        ///in functia compare se face compararea in functie de weight
        ///instatiez comparatorul
        ItemComparatorByWeight comparator = new ItemComparatorByWeight();
        Set<Item> itemsSet = new TreeSet<>(comparator);
        itemsSet.addAll(availableItems);
        for (Item item : itemsSet) {
            backpack.add(item);
        }
    }

    ////cel mai eficient mod de a adauga item-uri in ghiozdan
    ///ghiozdanul o da fie full in cazul asta deoarece pot sa adaug fractii din anumite item-uri
    public void moveFractionToBackpack() {
        Set<Item> itemSet = new TreeSet<>(availableItems);
        for (Item item : itemSet) {
            backpack.addFraction(item);
        }
    }
}

