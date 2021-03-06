import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOne {

    public static void main(String[] args) {


        String inputTwo = "";
        int depthOneBack = 0, depthTwoBack = 0, depthThreeBack = 0;

        int incrementCounter = 0;
        List<Integer> depths;
        String testInput = "199\n200\n208\n210\n200\n207\n240\n269\n260\n263";
        String[] depthStrings = testInput.split("\n");

        depths = Arrays.stream(depthStrings)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/day1_input.txt"));
            depthStrings = allLines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        depths = Arrays.stream(depthStrings)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int depth : depths) {
            System.out.println("Scanning depth: " + depth + " vs previous " + depthThreeBack);

            if (depthThreeBack == 0) {
                System.out.println("Still filling first few depths in...");
            } else {
                if (depth > depthThreeBack) {
                    incrementCounter++;
                    System.out.println(depth + " > " + depthThreeBack + " Increased! Increment counter: " + incrementCounter);

                } else {
                    System.out.println(depth + " !> " + depthThreeBack);
                }
            }

            depthThreeBack = depthTwoBack;
            depthTwoBack = depthOneBack;
            depthOneBack = depth;
        }
        System.out.println("Increments total: " + incrementCounter);

        //input list into array
        //iterate through list
        //compare item to previous item
        //if larger, increment increase depth list
    }
}
