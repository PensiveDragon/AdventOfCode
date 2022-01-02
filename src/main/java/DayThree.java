import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayThree {

    static String[] input;

    public static void main (String args[]) {
        System.out.println("moo");

        // Parse file into string[].
        // Go through string[] line by line, char by char adding chars to a corresponding bit sum array.
        // Sum of each bit array tells you how many 1s were present, if >500, it is a 1, <500 it is a 0.
        // Create two values from number and inverse number.
        // Multiply together for answer.

        input = parseInput();
        System.out.println(findBitLength(input));

    }


    public static int findBitLength (String[] input) {
        return input[0].length();
    }

    public static String[] parseInput() {

        String[] allInput;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/day3_input.txt"));
            allInput = allLines.toArray(new String[0]);
            System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
