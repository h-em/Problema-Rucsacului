import java.io.IOException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Problem problem = new Problem();

        problem.setBackpack(new Backpack(12));
    /*    List<Item> generatedItems = Util.generateItems(100);
        problem.setItemsList(generatedItems);
        //System.out.println(generatedItems);
        problem.displayNumberOfItems();
        problem.displayAveragePriceForEachItem();
        problem.displatTotalValueForEachItem();

        System.out.println();

        System.out.println("Not Sorted!");
        problem.moveItemsToBackpack();
        displayToConsole(problem);


        System.out.println("Sorted!");
        problem.setBackpack(new Backpack(12));
        problem.moveProfitableItemsToBackpack();
        displayToConsole(problem);


/*
        System.out.println("Try!");
        problem.setBackpack(new Backpack(12));
        problem.trySomething();
        displayToConsole(problem);


        System.out.println("Sorted + fraction of items!");
        problem.setBackpack(new Backpack(12));
        problem.moveFractionToBackpack();
        displayToConsole(problem);


        List<Item> auxGeneratedItems = new LinkedList<>(generatedItems);
        System.out.println("Sorted + fraction of items (each item will be deleted after adding it to backpack)!");
        problem.setBackpack(new Backpack(12));
        problem.moveFractionToBackpack2();
        displayToConsole(problem);


        System.out.println("Sorted by light items!");
        problem.setBackpack(new Backpack(12));
        problem.setItemsList(auxGeneratedItems);
        problem.moveLightItemsToBackpack();
        displayToConsole(problem);

*/
        problem.setBackpack(new Backpack(12));

       /* Scanner scanner = new Scanner(System.in);
        int addItem = 1;
        while(addItem == 1) {
            System.out.println("name: ");
            String itemName = scanner.nextLine();
            System.out.println("Value: ");
            Double itemValue = scanner.nextDouble();
            System.out.println("Weight: ");
            Double itemWeight = scanner.nextDouble();
            Item newItem = new Item(itemName,itemValue, itemWeight);
            problem.moveItemsToBackpackOneByOne(newItem);

            System.out.println("press 1 -> continue \n 0 -> exit");
            addItem = scanner.nextInt();
            scanner.nextLine();
*/
        Item item1 = new Item("phone",5, 5);
        problem.moveItemsToBackpackOneByOne(item1);
        Item item2 = new Item("water",7, 7);
        problem.moveItemsToBackpackOneByOne(item2);
        Item item3 = new Item("gold",8, 8);
        problem.moveItemsToBackpackOneByOne(item3);
        Item item4 = new Item("pen",1, 1);
        problem.moveItemsToBackpackOneByOne(item4);
        Item importantItem = new Item("passport",15, 8);
        problem.moveItemsToBackpackOneByOne(importantItem);

        System.out.println(problem.getBackpack().getItems());
/*
        Item item6 = new Item("book",20, 2.5);
        problem.moveItemsToBackpackOneByOne(item6);
        Item item5 = new Item("book2",25, 2);
        problem.moveItemsToBackpackOneByOne(item5);
        System.out.println(problem.getBackpack().getItems());
*/
    }

    private static void displayToConsole(Problem problem) {
        double totalWeightOfItemsInBackpack;
        double backpackCapacity;
        double totalValue;

        totalWeightOfItemsInBackpack = problem.getTotalWeightOfItemInBackpack();
        backpackCapacity = problem.getBackpackCapacity();
        totalValue = problem.getBackpackValue();
        System.out.println("Capacity: " + backpackCapacity + ", total weight in backpack: "
                + totalWeightOfItemsInBackpack + ", number of items in backpack: " + problem
                .getNumbersOfItemsInBackpack());
        System.out.println("Total value: " + totalValue + "\n\n");
    }

}
