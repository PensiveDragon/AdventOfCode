import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayTwo {

    static int distance = 0;
    static int depth = 0;
    static int aim = 0;

    public static void main (String[] args) {
        System.out.println("moo");

        String[] input = parseInput();

        //String[] testInput = {"forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2"};

        //readInput(input);
        advancedReadInput(input);

        System.out.println("The answer is: " + solvePuzzle(depth, distance));

        // Write method to parse through file
        // Two ints: distance and depth
        // Read file, adding for distance and adding/removing for depth
        // Multiply final distance by final depth
    }

    public static int solvePuzzle(int depth, int distance) {
        return depth * distance;
    }

    public static void readInput(String[] input) {

        for (String line : input){
            if (line.contains("up")) {
                depth -= Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
                System.out.println("Current depth = " + depth);
            } else if (line.contains("down")) {
                depth += Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
                System.out.println("Current depth = " + depth);
            } else if (line.contains("forward")) {
                distance += Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
                System.out.println("Current distance = " + distance);
            }
        }
    }

    public static void advancedReadInput(String[] input) {

        for (String line : input){
            if (line.contains("up")) {
                aim -= Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
                System.out.println("Current depth = " + depth + " | Current Aim = " + aim);
            } else if (line.contains("down")) {
                aim += Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
                System.out.println("Current depth = " + depth + " | Current Aim = " + aim);
            } else if (line.contains("forward")) {
                distance += Integer.parseInt(String.valueOf(line.charAt(line.length()-1)));
                depth += (aim * Integer.parseInt(String.valueOf(line.charAt(line.length()-1))));
                System.out.println("Current distance = " + distance + " | Current Depth = " + depth);

            }
        }
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
