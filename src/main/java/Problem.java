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


    public void moveFractionToBackpack2() {

        Set<Item> itemSet = new TreeSet<>(availableItems);
        for (Item item : itemSet) {
            backpack.addFraction(item);

            availableItems.remove(item);
        }
    }
/*

    public Map getNumberOfEachItems() {
        Map<String, Integer> mapOfNumberOfItems = new HashMap<>();

        int[] frArray = new int[availableItems.size()];

        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);
            int count = 1;
            for (int j = i + 1; j < availableItems.size(); j++) {
                Item item1 = availableItems.get(j);
                if(item.getName().equals(item1.getName())){
                    count++;
                    //pentru cele care au fost deja numarate
                    frArray[j] = -1;
                }
            }
            if(frArray[i] != -1){
                mapOfNumberOfItems.put(item.getName(),count);
            }
        }
        return mapOfNumberOfItems;
    }*/

    public Map getNumberOfEachItems() {
        Map<String, Integer> mapOfNumberOfItems = new HashMap<>();
        for (Item each : availableItems) {
            Integer numberOfEach = mapOfNumberOfItems.get(each.getName());
            if (numberOfEach == null) {
                numberOfEach = 1;
            } else {
                numberOfEach = numberOfEach + 1;
            }
            mapOfNumberOfItems.put(each.getName(), numberOfEach);
        }
        return mapOfNumberOfItems;
    }





    public void displayNumberOfItems() {
        Map<String, Item> items = getNumberOfEachItems();

        for (String item : items.keySet()) {
            System.out.println(item + ": " + items.get(item));
        }
        System.out.println();
    }


    public Map getAveragePriceForEachTypeOfItem() {

        Map<String, Double> mapOfAveragePrices = new HashMap<>();
        Map<String, Integer> numberOfItems = getNumberOfEachItems();

            for (Item eachItem : availableItems) {
                Double totalValueOfEach = Double.valueOf(numberOfItems.get(eachItem.getName()));
                if (totalValueOfEach == null) {
                    totalValueOfEach = eachItem.getValue();
                } else {
                    totalValueOfEach +=  eachItem.getValue();
                }
                mapOfAveragePrices.put(eachItem.getName(), totalValueOfEach / numberOfItems.get(eachItem.getName()));
            }
        return mapOfAveragePrices;
    }

    public void displayAveragePriceForEachItem() {
        Map<String, Item> items = getAveragePriceForEachTypeOfItem();

        for (String item : items.keySet()) {
            System.out.println(item + ": " + items.get(item));
        }
        System.out.println();
    }


}


