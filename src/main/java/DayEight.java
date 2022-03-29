import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayEight {

    // Reverse engineer input values to determine which numbers they correspond to
    // Find 1, 4, 7, 8 as they use unique numbers of inputs.

    static String testInputPath = "src/main/resources/day8_test_input.txt";
    static String inputPath = "src/main/resources/day8_input.txt";

    public static void main (String args[]) {
        System.out.println("Moo");
        parseInput(testInputPath);
    }

    public static ArrayList<String> parseInput (String path) {

        ArrayList<String> allInput = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));

            allInput.addAll(allLines);
            //System.out.println(allInput);
            System.out.println("Read in " + allInput.size() + " lines.");

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
