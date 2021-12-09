import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOne {

    public static void main(String[] args) {

        String testInput = "199\n200\n208\n210\n200\n207\n240\n269\n260\n263";
        String inputTwo;
        int depthLevel = Integer.MAX_VALUE;
        int incrementCounter = 0;

        String[] depthStrings = testInput.split("\n");


        List<Integer> depths = Arrays.stream(depthStrings)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int depth : depths) {
            System.out.println("Scanning depth: " + depth + " vs previous " + depthLevel);
            if (depth > depthLevel) {
                incrementCounter++;
                System.out.println(depth + " > " + depthLevel + " Increased! Increment counter: " + incrementCounter);

            } else {
                System.out.println(depth + " !> " + depthLevel);
            }
            depthLevel = depth;
        }
        System.out.println("Increments total: " + incrementCounter);

        //input list into array
        //iterate through list
        //compare item to previous item
        //if larger, increment increase depth list
    }
}
