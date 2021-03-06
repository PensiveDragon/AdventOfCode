import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DayThree {

    static String[] input;
    static int gammaRate = 0;
    static int epsilonRate = 0;
    static int oxygenRating = 0;
    static int co2Rating = 0;
    static int lifeSupportRating = 0;


    public static void main (String args[]) {
        System.out.println("moo");

        // Part 1
        // Parse file into string[].
        // Go through string[] line by line, char by char adding chars to a corresponding bit sum array.
        // Sum of each bit array tells you how many 1s were present, if >500, it is a 1, <500 it is a 0.
        // Create two values from number and inverse number.
        // Multiply together for answer.

        input = parseInput();
        //System.out.println(findBitLength(input));

        partTwoSearch(input);

        //sortInput(input);

        // Part 2:
        // We are finding numbers only with the most common number from each bit, starting from the left.
        // Take String[] input.
        // Select first bit.
        // Find most common value (equal values return 1 for oxygen, 0 for co2).
        // Keep numbers matching most common value.
        // Select next bit.
        // Repeat untill all 12 bits are found.
        // Repeat whole process again for co2.
        // Convert o2 and co2 binaries into numbers.
        // Multiply numbers together for life support rating.


    }

    public static void partTwoSearch(String[] input) {
        //System.out.println("Input length = " + input[0].length());
        ArrayList<String> oxygenArrayList = new ArrayList<>(Arrays.asList(input));
        ArrayList<String> co2ArrayList = new ArrayList<>(Arrays.asList(input));
        System.out.println("Starting Array Size: " + oxygenArrayList.size());
        String[] remainingList = input;
        for (int i = 0; i < (input[0].length()); i++) {

            int oxygenCorrectValue = findMostCommonValue(oxygenArrayList, i, "oxygen");
            oxygenArrayList = removeNonMatches(oxygenArrayList, oxygenCorrectValue, i);

        }

        System.out.println("Oxygen final binary: " + oxygenArrayList.get(0));

        for (int i = 0; i < (input[0].length()); i++) {

            int co2CorrectValue = findMostCommonValue(co2ArrayList, i, "co2");
            co2ArrayList = removeNonMatches(co2ArrayList, co2CorrectValue, i);
            if (co2ArrayList.size() == 1) {
                i = 12;
            }
        }

        System.out.println("CO2 final binary: " + co2ArrayList.get(0));

        oxygenRating = translateBinary(convertStringToIntArray(oxygenArrayList.get(0)));
        co2Rating = translateBinary(convertStringToIntArray(co2ArrayList.get(0)));

        System.out.println("Oxygen binary: " + oxygenArrayList.get(0) + " = " + oxygenRating);
        System.out.println("CO2 binary: " + co2ArrayList.get(0) + " = " + co2Rating);

        lifeSupportRating = oxygenRating * co2Rating;
        System.out.println("Life Support Rating: " + lifeSupportRating);

    }

    public static String displayBinaryIntArrayInString(int[] binaryArrayInput) {
        String result = "";
        for (int value : binaryArrayInput) {
            result += value;
        }
        System.out.println("Binary String result is: " + result);
        return result;
    }

    public static int[] convertStringToIntArray(String input){
        int[] result = new int[input.length()];
        System.out.println(input.length());
        for (int i = 0; i < input.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        //System.out.println(result);
        return result;
    }

    public static int findMostCommonValue(String[] input, int position, String mode) {
        int result = 0;
            for (String line : input) {
                //System.out.println("line: " + line + " | position: " + position);
                if (Character.getNumericValue(line.charAt(position)) == 0) {
                    result -= 1;
                } else if (Character.getNumericValue(line.charAt(position)) == 1){
                    result += 1;
                } else if (Character.getNumericValue(line.charAt(position)) == -1) {
                    if (mode.equals("oxygen")) {
                        result += 1;
                    } else if (mode.equals("co2")) {
                        result -= 1;
                    }
                } else {
                    System.out.println("Something went wrong!");
                }
            }
        //System.out.println("Most Common Value result = " + result);
        if (result < 0) {
            System.out.println("Most Common Value = 0");
            return 0;
        } else if (result > 0) {
            System.out.println("Most Common Value = 1");
            return 1;
        } else
            System.out.println("Most Common Value = Tied!");
        return -1;
    }

    public static int findMostCommonValue(ArrayList<String> input, int position, String mode) {
        int result = 0;
        for (String line : input) {
            //System.out.println("line: " + line + " | position: " + position);
            if (Character.getNumericValue(line.charAt(position)) == 0) {
                result -= 1;
            } else if (Character.getNumericValue(line.charAt(position)) == 1){
                result += 1;
            } else if (Character.getNumericValue(line.charAt(position)) == -1) {
                if (mode.equals("oxygen")) {
                    result += 1;
                } else if (mode.equals("co2")) {
                    result -= 1;
                }
            } else {
                System.out.println("Something went wrong!");
            }
        }
        //System.out.println("Most Common Value result = " + result);
        if (result < 0) {
            System.out.println("Most Common Value = 0");
            if (mode.equals("oxygen")) {
                return 0;
            } else if (mode.equals("co2")) {
                return 1;
            }
        } else if (result > 0) {
            System.out.println("Most Common Value = 1");
            if (mode.equals("oxygen")) {
                return 1;
            } else if (mode.equals("co2")) {
                return 0;
            }
        } else
            //System.out.println("Most Common Value = Tied!");
        if (mode.equals("oxygen")) {
            System.out.println("Most Common Value = Tied! (Oxygen Picking 1)");
            result = 1;
        } else if (mode.equals("co2")) {
            System.out.println("Most Common Value = Tied! (CO2 Picking 0)");
            result = 0;
        }
        return result;
    }

    public static ArrayList<String> removeNonMatches(ArrayList<String> input, int correctValue, int bitIndex) {

        //System.out.println("Removing Non Matches...");
        System.out.println("DEBUG: correctValue=" + correctValue + " bitIndex=" + bitIndex + " inputSize=" + input.size() + " input=" + input);
        ArrayList<String> result = new ArrayList<String>();

        for (String line : input) {
            if (Character.getNumericValue(line.charAt(bitIndex)) == correctValue) {
                result.add(line);
                //System.out.println("Added: " + line);
            }
        }



        return result;
    }

    public static int[] sortByBitCriteria(int[] input, String mode) {

        for (int line : input) {
            int position = 0;
            for (int i = 0; i < String.valueOf(line).length(); i++) {
                String.valueOf(line).charAt(position);
            }
        }


        if (mode.equals("oxygen")) {

        } else if (mode.equals("co2Rating")) {

        } else {

        }

        return null;
    }

    public static void sortInput(String[] input) {

        int[] bitArray = new int[findBitLength(input)];

        for (String line : input) {
            //System.out.println("Checking line: " + line);
            for (int i = 0; i < line.length(); i++) {
                char cNum = line.charAt(i);
                int iNum = Integer.parseInt(String.valueOf(cNum));
                //System.out.println("Checking num: " + num);
                if (iNum == 1) {
                    bitArray[i] += iNum;
                    System.out.println("bitArray[" + i + "] = " + bitArray[i]);
                }
            }
        }
        gammaRate = translateBinary(bitArrayTotals(bitArray));

        epsilonRate = translateBinary(invertBinary(bitArrayTotals(bitArray)));

        System.out.println("Answer = " + (gammaRate * epsilonRate));
    }

    public static int translateBinary(int[] binaryInput) {
        int result = 0;

        for (int i = binaryInput.length-1, j = 0; i >= 0; i--, j++) {
            if (binaryInput[i] == 1) {
                result += (int) Math.pow(2,j);
                //System.out.println("Result = " + result);
            }
            //System.out.println((int) Math.pow(2,(i)));
        }

        return result;
    }

    public static int[] invertBinary(int[] binaryInput) {
        int[] invertedBinary = new int[binaryInput.length];
        String result = "";
        for (int i = 0; i < binaryInput.length; i++) {
            if (binaryInput[i] == 1) {
                invertedBinary[i] = 0;
                result += 0;
            } else {
                invertedBinary[i] = 1;
                result += 1;
            }
        }

        System.out.println(result);

        return invertedBinary;
    }

    public static int[] bitArrayTotals(int[] bitArray){
        System.out.println();
        int[] results = new int[bitArray.length];
        String result = "";
        for (int i = 0; i < bitArray.length; i++) {
            System.out.println("bitArray[" + i + "] = " + bitArray[i]);
            if (bitArray[i] <= 500) {
                results[i] = 0;
                result += 0;
            } else {
                results[i] = 1;
                result += 1;
            }
        }

        System.out.println(result);

        return results;
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
