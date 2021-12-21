import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        String newString = "";

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        for (String line : allInput) {
            //System.out.println(line);
            if (line.length() > 10) {
                existingString = line;
            } else if (!line.isEmpty()) {
                // create a map to store polymer conversion
                String key = "";
                String result = "";
                key = String.valueOf(line.charAt(0));
                key += String.valueOf(line.charAt(1));
                result = String.valueOf(line.charAt(6));
                linkedHashMap.put(key, result);
            }
        }

        System.out.println(existingString);

        for (int i = 0; i < existingString.length()-1; i++) {
            //System.out.println(existingString.charAt(i));
            String pairToCheck = "";
            pairToCheck += existingString.subSequence(i, i+2);
            System.out.println("Pair to check = " + pairToCheck);

            newString += existingString.subSequence(i,i+1);

            for (Map.Entry m:linkedHashMap.entrySet()){
                if (pairToCheck.equals(m.getKey())) {
                    System.out.println(pairToCheck + " matches " + m.getKey() + "! We got one!");
                    newString += m.getValue();
                }
                //System.out.println(m.getKey() + " " + m.getValue());
            }

        }
        newString += existingString.subSequence(existingString.length()-1, existingString.length());

        System.out.println(existingString);
        System.out.println(newString);

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
            //System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
