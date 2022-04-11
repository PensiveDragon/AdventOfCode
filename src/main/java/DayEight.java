import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayEight {

    // Reverse engineer input values to determine which numbers they correspond to
    // Find 1, 4, 7, 8 as they use unique numbers of inputs.

    static String testInputPath = "src/main/resources/day8_test_input.txt";
    static String inputPath = "src/main/resources/day8_input.txt";

    static int numberOfUniqueValues = 0;

    public static void main (String args[]) {
        System.out.println("Moo");
        ArrayList<String> input = parseInput(testInputPath);
        iterateList(input);

    }

    public static void addToList (char letter) {

    }

    public static void mapNumbers (ArrayList<String> line) {
        // work out what elements appear in each number uniquely
        // if in 3 segment number , but not in 2 segment number = top cell
        // cell number that only appears 6 times = top left
        // cell number that only appears 8 times & has a value from 1 = top right
        // cell number that only appears 8 times & does not have a value from 7 = middle
        // cell number that only appears 4 times = bottom left
        // cell number that only appears 9 times = bottom right
        // cell number that only appears 7 times = bottom

        System.out.println("Decyphering line: " + line);



        for (String word : line) {

            LettersList lettersList = new LettersList();

            for (int position = 0; position < word.length(); position++) {
                char letter = word.charAt(position);
                System.out.println(letter);
                lettersList.addLetter(letter);

            }


        }

        for (String word : line) {
            if (word.length() == 2) {
                System.out.println("We have found the number 1");
            } else if (word.length() == 4) {
                System.out.println("We have found the number 4");
            } else if (word.length() == 3) {
                System.out.println("We have found the number 7");
            } else if (word.length() == 7) {
                System.out.println("We have found the number 8");
            }
        }

    }

    public static void countUniqueValues (ArrayList<String> lineOutput) {

        for (String string : lineOutput) {
            if (string.length() == 2) {
                System.out.println("This is a 1!");
                numberOfUniqueValues++;
            } else if (string.length() == 4) {
                System.out.println("This is a 4!");
                numberOfUniqueValues++;
            } else if (string.length() == 3) {
                System.out.println("This is a 7!");
                numberOfUniqueValues++;
            } else if (string.length() == 7) {
                System.out.println("This is a 8!");
                numberOfUniqueValues++;
            } else {
                System.out.println("This is another number");
            }
        }
        System.out.println("There are this many unique value numbers: " + numberOfUniqueValues);
    }

    public static ArrayList<String> parseLineOutputValues (String line) {
        ArrayList<String> split = new ArrayList<>(Arrays.asList(line.split("\\|")));
        ArrayList<String> parsedNumbersList = new ArrayList<>(Arrays.asList(split.get(1).trim().split(" ")));

        return parsedNumbersList;
    }

    public static ArrayList<String> parseLineNumbers (String line) {
        ArrayList<String> split = new ArrayList<>(Arrays.asList(line.split("\\|")));
        ArrayList<String> parsedNumbersList = new ArrayList<>(Arrays.asList(split.get(0).split(" ")));

        return parsedNumbersList;
    }

    public static void iterateList (ArrayList<String> inputArrayList) {
        for (String line : inputArrayList) {
            System.out.println(line);

            ArrayList<String> lineNumbers = parseLineNumbers(line);
            //System.out.println(lineNumbers);

            ArrayList<String> lineOutput = parseLineOutputValues(line);
            //System.out.println(lineOutput);

            //countUniqueValues(lineOutput);
            mapNumbers(lineNumbers);
        }
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

class LettersList {
    HashMap<Character, Integer> charList = new HashMap<>();
/*
    public lettersList(HashMap<Character, Integer> charList) {
        this.charList = charList;
    }
*/
    public void addLetter (Character letter) {
        if (charList.get(letter) == null) {
            charList.put(letter, 0);
        } else if (charList.get(letter) != null) {
            charList.put(letter, charList.get(letter) + 1);
        } else {
            System.out.println("Error: shouldn't have got here!");
        }

    }
}
