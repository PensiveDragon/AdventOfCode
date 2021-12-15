import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayForteen {



    public static void main(String[] args) {
        // Repeat 10 times
        // > Take String existingString
        // > Iterate two letters through the loop
        // > Merge the two letters into a pair
        // > Compare the pair against the list of options
        // > If a match is found, find the new character
        // > Add the letters to the String nextString
        // Count the number of each letter in the final String
        // Subtract the lowest number from the  highest number.

        String[] allInput = parseInput();

        String existingString = "";

        for (String line : allInput) {
            if (line.length() > 10) {
                existingString = line;
            }
            else if (!line.isEmpty()) {
                // create a map to store polymer conversion
            }
        }

        System.out.println(existingString);



        /*
        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
        */
    }

    public static String[] parseInput() {

        String[] allInput;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/day14_input.txt"));
            allInput = allLines.toArray(new String[0]);
            System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
