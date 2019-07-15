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
    /*
    public void trySomething() {
        ///introduc lista mea de item-uri in threeSet ca se fie sortate dupa ratia dintre value/weight!!
        //in functia compareTo() din Item este toata magia
        ItemComparatorForUselessItems comparator = new ItemComparatorForUselessItems();
        Set<Item> itemSet = new TreeSet<>(comparator);
        itemSet.addAll(availableItems);
        for (Item item : itemSet) {
            backpack.add(item);
        }
    }*/


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
            backpack.addItemOrFractionOfAnItem(item);
        }
    }


    public void moveFractionToBackpack2() {

        Set<Item> itemSet = new TreeSet<>(availableItems);
        for (Item item : itemSet) {
            if(backpack.addItemOrFractionOfAnItem(item)) {
                availableItems.remove(item);
            }
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
                numberOfEach += 1;
            }
            mapOfNumberOfItems.put(each.getName(), numberOfEach);


            //!!!!!!!!MAI SIMPLU
            /*if (numberOfEach == null) {
                numberOfEach = 0;
            }
            mapOfNumberOfItems.put(each.getName(), numberOfEach+1);
            */


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


    public Map getTotalValueForEachItem(){

        Map<String, Double> mapOfTotalValueOfEachIndex = new HashMap<>();

        for (Item eachItem : availableItems) {
            Double totalValueOfEach = mapOfTotalValueOfEachIndex.get(eachItem.getName());
            if (totalValueOfEach == null) {
                totalValueOfEach = eachItem.getValue();
            } else {
                totalValueOfEach +=  eachItem.getValue();
            }
            mapOfTotalValueOfEachIndex.put(eachItem.getName(), totalValueOfEach);
        }
        return mapOfTotalValueOfEachIndex;
    }


    public void displatTotalValueForEachItem() {
        Map<String, Item> items = getTotalValueForEachItem();

        System.out.println("Displat total value for each item!");
        for (String item : items.keySet()) {
            System.out.println(item + ": " + items.get(item));
        }
        System.out.println();
    }



    public Map getAveragePriceForEachTypeOfItem() {

        Map<String, Double> mapOfAveragePrices = new HashMap<>();
        Map<String, Integer> numberOfItems = getNumberOfEachItems();

            for (Item eachItem : availableItems) {
                Double totalValueOfEach = mapOfAveragePrices.get(eachItem.getName());
                if (totalValueOfEach == null) {
                    totalValueOfEach = eachItem.getValue();
                } else {
                    double numberOfEachItem =  numberOfItems.get(eachItem.getName());
                    totalValueOfEach *= numberOfEachItem; // refac suma de valori a item-urilor din map
                    totalValueOfEach +=  eachItem.getValue();
                }
                mapOfAveragePrices.put(eachItem.getName(), totalValueOfEach / numberOfItems.get(eachItem.getName()));
            }
        return mapOfAveragePrices;
    }

    public void displayAveragePriceForEachItem() {
        Map<String, Item> items = getAveragePriceForEachTypeOfItem();

        System.out.println("Display average price for each item!");

        for (String item : items.keySet()) {
            System.out.println(item + ": " + items.get(item));
        }
        System.out.println();
    }
/*
    public Map getAveragePriceForEachTypeOfItem2() {

        Map<String, Double> mapOfAveragePrices = new HashMap<>();
        Map<String, Integer> numberOfItems = getNumberOfEachItems();

        int[] frArray = new int[availableItems.size()];

        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);
            double totalValue = item.getValue();
            for (int j = i + 1; j < availableItems.size(); j++) {
                Item item1 = availableItems.get(j);
                if(item.getName().equals(item1.getName())){
                    totalValue+=item.getValue();
                    //pentru cele care au fost deja numarate
                    frArray[j] = -1;
                }
            }
            if(frArray[i] != -1){
                mapOfAveragePrices.put(item.getName(), totalValue / numberOfItems.get(item.getName()));
            }
        }

        return mapOfAveragePrices;
    }


    public void displayAveragePriceForEachItem2() {
        Map<String, Item> items = getAveragePriceForEachTypeOfItem2();

        for (String item : items.keySet()) {
            System.out.println(item + ": " + items.get(item));
        }
        System.out.println();
    }
*/



    public void moveItemsToBackpackOneByOne(Item item) {
            backpack.addItemOrFractionOfAnItemOneByOne(item);
    }

}


