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


    public void moveFractionToBackpackWithTreeMap() {

        Map<Item, Boolean> itemMap = new TreeMap<>();
        for (Item item : availableItems) {
            itemMap.put(item, true);
        }
        for (Item item : itemMap.keySet()) {
            if (itemMap.get(item) == true) {
                backpack.addFraction(item);
                itemMap.put(item, false);
            }
        }
    }

    public void moveFractionToBackpackWithQueue() {

        Set<Item> itemSet = new TreeSet<>(availableItems);
        Queue<Item> itemsQueue = new LinkedList<>(itemSet);

        while (itemsQueue.isEmpty() != false || backpack.getTotalWeightOfItems() < backpack.getCapacity()) {
            Item item = itemsQueue.poll();
            backpack.addFraction(item);
        }
    }

    public Map getNumberOfItemsWeHave() {
        Map<String, Integer> mapOfNumberOfItems = new HashMap<>();

        int nrOflaptop = 0;
        int bottle = 0;
        int phone = 0;
        int sunglasses = 0;
        int book = 0;
        int pen = 0;
        int radio = 0;

        for (Item item : availableItems) {
            switch (item.getName()) {
                case "laptop":
                    nrOflaptop++;
                    mapOfNumberOfItems.put("laptop", nrOflaptop);
                    break;
                case "bottle":
                    bottle++;
                    mapOfNumberOfItems.put("bottle", bottle);
                    break;
                case "phone":
                    phone++;
                    mapOfNumberOfItems.put("phone", phone);
                    break;
                case "sunglasses":
                    sunglasses++;
                    mapOfNumberOfItems.put("sunglasses", sunglasses);
                    break;
                case "book":
                    book++;
                    mapOfNumberOfItems.put("book", book);
                    break;
                case "pen":
                    pen++;
                    mapOfNumberOfItems.put("pen", pen);
                    break;
                case "radio":
                    radio++;
                    mapOfNumberOfItems.put("radio", radio);
                    break;
            }
        }
        return mapOfNumberOfItems;
    }

    public void displayNumberOfItems() {
        Map<String, Item> items = getNumberOfItemsWeHave();

        for (String item : items.keySet()) {
            System.out.println(item + ": " + items.get(item));
        }
        System.out.println();
    }


    public Map getAveragePriceForEachTypeOfItem() {

        Map<String, Double> mapOfAveragePrices = new HashMap<>();

        Map<String, Integer> numberOfItems = getNumberOfItemsWeHave();

        double valueOfLaptops = 0;
        double valueOfBottles = 0;
        double valueOfPhones = 0;
        double valueOfSunglases = 0;
        double valueOfBooks = 0;
        double valueOfPens = 0;
        double valueOfRadios = 0;

        for (Item item : availableItems) {
            switch (item.getName()) {
                case "laptop":
                    valueOfLaptops += item.getValue();
                    break;
                case "bottle":
                    valueOfBottles += item.getValue();
                    break;
                case "phone":
                    valueOfPhones += item.getValue();
                    break;
                case "sunglasses":
                    valueOfSunglases += item.getValue();
                    break;
                case "book":
                    valueOfBooks += item.getValue();
                    break;
                case "pen":
                    valueOfPens += item.getValue();
                    break;
                case "radio":
                    valueOfRadios += item.getValue();
                    break;
            }
        }
        mapOfAveragePrices.put("laptop", valueOfLaptops / numberOfItems.get("laptop"));
        mapOfAveragePrices.put("bottle", valueOfBottles / numberOfItems.get("bottle"));
        mapOfAveragePrices.put("phone", valueOfPhones / numberOfItems.get("phone"));
        mapOfAveragePrices.put("sunglasses", valueOfSunglases / numberOfItems.get("sunglasses"));
        mapOfAveragePrices.put("book", valueOfBooks / numberOfItems.get("book"));
        mapOfAveragePrices.put("pen", valueOfPens / numberOfItems.get("pen"));
        mapOfAveragePrices.put("radio", valueOfRadios / numberOfItems.get("radio"));

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


