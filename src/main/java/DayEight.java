import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayEight {

    // Reverse engineer input values to determine which numbers they correspond to
    // Find 1, 4, 7, 8 as they use unique numbers of inputs.

    static String testInputPath = "src/main/resources/day8_test_input.txt";
    static String inputPath = "src/main/resources/day8_input.txt";

    static int numberOfUniqueValues = 0;

    public static void main(String args[]) {
        System.out.println("Moo");
        ArrayList<String> input = parseInput(testInputPath);
        iterateList(input);

    }

    public static String translateNumberDisplay(HashMap<Integer, String> translationMap, ArrayList<String> numbersString) {

        String result = "";

        for (String codelet : numbersString) {
            System.out.println(codelet);
            for (int i = 0; i < translationMap.size(); i++) {
                System.out.println("i = " + i + " codelet = " + codelet + " translation: " + translationMap.get(i));
                if (codelet.equals(translationMap.get(i))) {
                    System.out.println(i);
                    result += i;
                }
            }
        }

        return result;
    }

    public static HashMap<Integer, String> mapSegmentsToNumbers(HashMap<String, Character> segmentsMap, ArrayList<String> line) {

        System.out.println("Segments Map reads: " + segmentsMap + ". Line is: " + line + " Length: " + line.size());

        HashMap<Integer, String> results = new HashMap<>();

        //for each Code in line:
        // - count length, identify all the easy numbers
        // - for ambiguous ones, check for identifying segments
        // - assign number Codes to numberMap
        // return numberMap

        for (String code : line) {
            switch (code.length()) {
                case 2:
                    System.out.println("Segments " + code + " = number 1");
                    results.put(1, code);
                    break;
                case 3:
                    System.out.println("Segments " + code + " = number 7");
                    results.put(7, code);
                    break;
                case 4:
                    System.out.println("Segments " + code + " = number 4");
                    results.put(4, code);
                    break;
                case 5:
                    if (code.contains(Character.toString(segmentsMap.get("Lower Left")))) {
                        System.out.println("Segments " + code + " = number 2");
                        results.put(2, code);
                    } else if (code.contains(Character.toString(segmentsMap.get("Upper Left")))) {
                        System.out.println("Segments " + code + " = number 5");
                        results.put(5, code);
                    } else {
                        System.out.println("Segments " + code + " = number 3");
                        results.put(3, code);
                    }
                    break;
                case 6:
                    if (!code.contains(Character.toString(segmentsMap.get("Middle")))) {
                        System.out.println("Segments " + code + " = number 0");
                        results.put(0, code);
                        break;
                    } else if (code.contains(Character.toString(segmentsMap.get("Lower Left")))) {
                        System.out.println("Segments " + code + " = number 6");
                        results.put(6, code);
                    } else {
                        System.out.println("Segments " + code + " = number 9");
                        results.put(9, code);
                    }
                    break;
                case 7:
                    System.out.println("Segments " + code + " = number 8");
                    results.put(8, code);
                    break;
            }
        }

        return results;
    }

    public static HashMap<Integer, String> mapNumbers(ArrayList<String> line) {
        // work out what elements appear in each number uniquely
        // if in 3 segment number , but not in 2 segment number = top cell
        // cell number that only appears 6 times = top left
        // cell number that only appears 8 times & has a value from 1 = top right
        // cell number that only appears 7 times & has a value from 4 = middle
        // cell number that only appears 4 times = bottom left
        // cell number that only appears 9 times = bottom right
        // cell number that only appears 7 times & does not have a value from 4 = bottom

        System.out.println("Decyphering line: " + line);

        LettersList lettersList = new LettersList();

        for (String word : line) {

            for (int position = 0; position < word.length(); position++) {
                char letter = word.charAt(position);
                //System.out.println(letter);
                lettersList.addLetter(letter);
                //System.out.println(lettersList);
            }
        }

        //lettersList.showList();
        return mapSegmentsToNumbers(lettersList.identifySegments(line), line);
/*
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

 */
    }

    public static void countUniqueValues(ArrayList<String> lineOutput) {

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

    public static ArrayList<String> parseLineOutputValues(String line) {
        ArrayList<String> split = new ArrayList<>(Arrays.asList(line.split("\\|")));
        ArrayList<String> parsedNumbersList = new ArrayList<>(Arrays.asList(split.get(1).trim().split(" ")));

        return parsedNumbersList;
    }

    public static ArrayList<String> parseLineNumbers(String line) {
        ArrayList<String> split = new ArrayList<>(Arrays.asList(line.split("\\|")));
        ArrayList<String> parsedNumbersList = new ArrayList<>(Arrays.asList(split.get(0).split(" ")));

        return parsedNumbersList;
    }

    public static void iterateList(ArrayList<String> inputArrayList) {
        for (String line : inputArrayList) {
            System.out.println(line);

            ArrayList<String> lineNumbers = parseLineNumbers(line);
            //System.out.println(lineNumbers);

            ArrayList<String> lineOutput = parseLineOutputValues(line);
            //System.out.println(lineOutput);

            //countUniqueValues(lineOutput);
            HashMap<Integer, String> characterHashMap = mapNumbers(lineNumbers);
            System.out.println(characterHashMap);
            System.out.println("Number Display: " + translateNumberDisplay(characterHashMap, lineOutput));
        }
    }

    public static ArrayList<String> parseInput(String path) {

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
    public void addLetter(Character letter) {
        if (charList.get(letter) == null) {
            charList.put(letter, 1);
        } else if (charList.get(letter) != null) {
            charList.put(letter, charList.get(letter) + 1);
        } else {
            System.out.println("Error: shouldn't have got here!");
        }
    }

    public HashMap<String, Character> identifySegments(ArrayList<String> line) {

        HashMap<String, Character> segmentsOrderResult = new HashMap<>(7);

        boolean found;
        System.out.println("Identifying Segments in line: " + line);

        for (char key : charList.keySet()) {
            switch (charList.get(key)) {
                case 4:
                    System.out.println("Segment " + key + " is Lower Left");
                    segmentsOrderResult.put("Lower Left", key);
                    break;

                case 6:
                    System.out.println("Segment " + key + " is Upper Left");
                    segmentsOrderResult.put("Upper Left", key);
                    break;

                case 7:
                    // if contains letters for no.4 = mid, otherwise lower mid
                    //System.out.println("Which 7 is it?");
                    found = false;
                    //System.out.println(line);
                    for (String numCell : line) { //for each segment description in the line
                        if (numCell.length() == 4) { //if it is the 4 length one
                            for (int i = 0; i < numCell.length(); i++) { //check if any of the letters within match...
                                //System.out.println("Checks char: " + numCell.charAt(i));
                                //System.out.println("Against Key: " + key);
                                if (numCell.charAt(i) == key) { //... match the letters that make up
                                    System.out.println("Segment " + key + " is Middle");
                                    segmentsOrderResult.put("Middle", key);
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Segment " + key + " is Lower Middle");
                        segmentsOrderResult.put("Lower Middle", key);
                    }
                    break;

                case 8:
                    // if contains letters for no.1 = upper right, otherwise upper mid
                    //System.out.println("Which 8 is it?");
                    found = false;
                    //System.out.println(line);
                    for (String numCell : line) {
                        if (numCell.length() == 2) {
                            for (int i = 0; i < numCell.length(); i++) {
                                //System.out.println("Checks char: " + numCell.charAt(i));
                                //System.out.println("Against Key: " + key);
                                if (numCell.charAt(i) == key) {
                                    System.out.println("Segment " + key + " is Upper Right");
                                    segmentsOrderResult.put("Upper Right", key);
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Segment " + key + " is Upper Middle");
                        segmentsOrderResult.put("Upper Middle", key);
                    }
                    break;

                case 9:
                    System.out.println("Segment " + key + " is Lower Right");
                    segmentsOrderResult.put("Lower Right", key);
                    break;

                default:
                    System.out.println("Something went wrong.");
                    break;
            }
            //System.out.println("Segment " + key + " appears " + charList.get(key) + " times.");
        }

        System.out.println("Section identified!");

        return segmentsOrderResult;
    }

    public void showList() {

        for (char key : charList.keySet()) {
            System.out.println("Segment " + key + " appears " + charList.get(key) + " times.");
        }
    }
}

