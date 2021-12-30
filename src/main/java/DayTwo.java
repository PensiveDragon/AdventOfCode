import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayTwo {

    public static void main (String[] args) {
        System.out.println("moo");

        String[] input = parseInput();

        // Write method to parse through file
        // Two ints: distance and depth
        // Read file, adding for distance and adding/removing for depth
        // Multiply final distance by final depth
    }

    public static String[] parseInput() {

        String[] allInput;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/day2_input.txt"));
            allInput = allLines.toArray(new String[0]);
            System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
