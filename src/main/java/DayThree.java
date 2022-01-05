import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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

        sortInput(input);

    }

/*    public static void createBitArrays (int arrayCount) {
        arrayCount = findBitLength(input);
        for (int i = 0; i < arrayCount; i++) {
            String[] ("array"+arrayCount) = new String[];
        }
    }*/

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
        bitArrayTotals(bitArray);
    }



    public static int[] bitArrayTotals(int[] bitArray){
        System.out.println();
        int[] results = new int[bitArray.length+1];
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
