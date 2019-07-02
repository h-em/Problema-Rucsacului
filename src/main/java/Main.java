import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        Problem problem = new Problem();
        problem.setBackpack(new Backpack(12));
        List<Item> generatedItems = Util.generateItems(100);
        problem.setItemsList(generatedItems);

        System.out.println("Not Sorted!");
        problem.moveItemsToBackpack();
        displayToConsole(problem);
        //System.out.println(problem.getItems());

        
        System.out.println("Sorted!");
        problem.setBackpack(new Backpack(12));
        problem.moveProfitableItemsToBackpack();
        displayToConsole(problem);
        //System.out.println(problem.getItems());


        System.out.println("Sorted + fraction of items!");
        problem.setBackpack(new Backpack(12));
        problem.moveFractionToBackpack();
        displayToConsole(problem);
        //System.out.println(problem.getItems());


        System.out.println("Sorted by light items!");
        problem.setBackpack(new Backpack(12));
        problem.moveLightItemsToBackpack();
        displayToConsole(problem);
        //System.out.println(problem.getItems());




    }
        private static void displayToConsole (Problem problem){
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
