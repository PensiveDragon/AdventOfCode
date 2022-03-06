import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayFive {

    // Parse List input
    // Create X,X size Grid
    // For each line of input
    // -> Use coords to update grid with lines
    // -> Each line adds 1 to the coords listed
    // Count number of coords >1

    public static void main(String[] args) {
        System.out.println("Moo");

        System.out.println("Boop");

        String testInputPath = "src/main/resources/day5_test_input.txt";
        parseInput(testInputPath);
        int[][] field = generateField(10);
        displayField(field);
    }

    public static String[] parseInput (String path) {

        String[] allInput;

        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            allInput = allLines.toArray(new String[0]);
            System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void displayField (int[][] field) {
        for (int[] line : field) {
            for (int cell : line) {
                System.out.print(cell + " , ");
            }
            System.out.println("");
        }
    }

    public static int[][] generateField (int size) {
        int[][] field = new int[size][size];

        return field;
    }
}
