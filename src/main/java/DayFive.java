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
        String[] input = parseInput(testInputPath);

        int[][] field = generateField(findBoardSize(input));
        displayField(field);
    }

    public static int findBoardSize (String[] input) {
        int result = 0;
        for (String line : input) {
            System.out.println(line);
            line = line.replace(" -> ", ",");
            System.out.println(line);
            String[] stringNums = line.split(",");
            for (String stringNum : stringNums) {
                if (result < Integer.parseInt(stringNum)) {
                    result = Integer.parseInt(stringNum);
                }
            }
        }
        return result + 1;
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
