import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayFour {

    // Puzzle input: n*(5x5) boards = 3d array?
    // Puzzle input: Array(m) of drawn numbers (0-99)

    // Parse input data into both data structures
    // Cycle through drawn numbers. For each number:
    // - Check through each board, marking the found number in each (updating the found number in each to a *)?
    // - Check whether any board has won yet
    // On win, sum all unmarked numbers on winning board, multiply by last number drawn.

    public static void main (String args[]) {
        System.out.println("moo");

        String[] input = parseInput();
    }

    public static String[] parseInput() {

        String[] allInput;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/day4_input.txt"));
            allInput = allLines.toArray(new String[0]);
            System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
