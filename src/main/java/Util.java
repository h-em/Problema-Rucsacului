import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

    public static final String PATH_TO_FILE = "problem.json";
    public static final String[] itemNamesList = {"laptop", "bottle", "phone",
            "sunglasses", "book", "pen", "radio"};

    public static Problem loadProblem() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(PATH_TO_FILE);
        Reader reader = new InputStreamReader(fis);

        Gson gson = new GsonBuilder().create();
        Problem problem = gson.fromJson(reader, Problem.class);
        return problem;
    }

    public static void saveProblem(Problem problem) throws IOException {
        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(PATH_TO_FILE), "UTF-8")) {
            Gson gson = new GsonBuilder().create();

            gson.toJson(problem, writer);


        }
    }
    public static List<Item> generateItems ( int howMany){
        Random rand = new Random();
        List<Item> itemsList = new ArrayList<>();
        for (int index = 0; index < howMany; index++) {
            int indexForName = rand.nextInt(itemNamesList.length);

            String itemName = itemNamesList[indexForName];
            double weight = rand.nextDouble() * 10;
            double value = rand.nextDouble() * 100;

            Item item = new Item(itemName, value, weight);
            itemsList.add(item);
        }
        return itemsList;
    }
}