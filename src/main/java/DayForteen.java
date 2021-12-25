import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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


        // Part 2
        // Replace strings with hashmaps
        // change parser to parse input string into sets of pairs that populate the hashmap
        //

        String[] allInput = parseInput();

        String existingString = "";
        String newString = "";

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        for (String line : allInput) {
            if (!line.isEmpty()) {
                if (line.contains("->")) {
                    // create a map to store polymer conversion
                    String key = "";
                    String result = "";
                    key = String.valueOf(line.charAt(0));
                    key += String.valueOf(line.charAt(1));
                    result = String.valueOf(line.charAt(6));
                    linkedHashMap.put(key, result);
                } else {
                    existingString = line;
                }
            }
        }

        Map<String, Integer> testParse = parseIntoPairMap(existingString);
/*
        // Display new Pair Map
        for (Map.Entry<String, Integer> pair: testParse.entrySet()) {
            System.out.println(pair);
        }
*/
        pairMapIntoPairMap(testParse, linkedHashMap);


/*
        for (int j = 0; j < 10; j++) {

            for (int i = 0; i < existingString.length() - 1; i++) {
                //System.out.println(existingString.charAt(i));
                String pairToCheck = "";
                pairToCheck += existingString.subSequence(i, i + 2);
                //System.out.println("Pair to check = " + pairToCheck);

                newString += existingString.subSequence(i, i + 1);

                for (Map.Entry m : linkedHashMap.entrySet()) {
                    if (pairToCheck.equals(m.getKey())) {
                        //System.out.println(pairToCheck + " matches " + m.getKey() + "! We got one!");
                        newString += m.getValue();
                    }
                    //System.out.println(m.getKey() + " " + m.getValue());
                }

            }
            newString += existingString.subSequence(existingString.length() - 1, existingString.length());

            //System.out.println(existingString);
            System.out.println(newString);

            existingString = newString;
            newString = "";

        }
*/
/*
        Map<Character, Integer> characterFrequencies = countAllCharacters(existingString);

        int largestNo = 0;
        int smallestNo = Integer.MAX_VALUE;

        for (Map.Entry<Character, Integer> character: characterFrequencies.entrySet()) {
            System.out.println(character);

            if (character.getValue() > largestNo) {
                largestNo = character.getValue();
            }

            if (character.getValue() < smallestNo) {
                smallestNo = character.getValue();
            }
        }
        int answer = largestNo - smallestNo;
        System.out.println(answer);
*/
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

    public static Map<Character, Integer> countAllCharacters(String input) {
        Map<Character,Integer> frequencies = new TreeMap<>();
        for (char ch : input.toCharArray())
            frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);

        return frequencies;
    }

    public static Map<String, Integer> parseIntoPairMap(String input) {
        Map<String, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < input.length() - 1; i++) {
            //System.out.println(existingString.charAt(i));
            String pairToStore = "";
            pairToStore += input.subSequence(i, i + 2);
            frequencies.put(pairToStore, frequencies.getOrDefault(pairToStore,0)+1);
        }

        return frequencies;
    }

    public static Map<String, Integer> pairMapIntoPairMap(Map<String, Integer> input, LinkedHashMap<String, String> lookUpMap) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<String, Integer> pair: input.entrySet()) {
            System.out.println(pair.getKey() + " creates " + matchPairToProduct(pair.getKey(), lookUpMap));
            String newPairOne, newPairTwo = "";
            newPairOne = pair.getKey().substring(0,1) + matchPairToProduct(pair.getKey(), lookUpMap);
            newPairTwo = matchPairToProduct(pair.getKey(), lookUpMap) + pair.getKey().substring(1,2);
            System.out.println("New Pairs: " + newPairOne + " | " + newPairTwo);
        }

        return result;
    }

    public static String matchPairToProduct (String pairToCheck, LinkedHashMap<String, String> lookUpMap) {

        LinkedHashMap<String, String> linkedHashMap = lookUpMap;
        String result = "";

        for (Map.Entry m : linkedHashMap.entrySet()) {
            if (pairToCheck.equals(m.getKey())) {
                //System.out.println(pairToCheck + " matches " + m.getKey() + "! We got one!");
                result += m.getValue();
            }
            //System.out.println(m.getKey() + " " + m.getValue());
        }
        return result;
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
