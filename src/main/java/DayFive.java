import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFive {

    // Parse List input
    // For each line of input
    // -> Use coords to update grid with lines
    // -> Each line adds 1 to the coords listed
    // Count number of coords >1

    public static void main(String[] args) {
        System.out.println("Moo");
        System.out.println("Boop");

        String testInputPath = "src/main/resources/day5_test_input.txt";
        ArrayList<Coordinates> input = parseInput(testInputPath);

        //int[][] field = generateField(findBoardSize(input));
        int[][] field = generateField(findBoardSize2(input));
        displayField(field);
    }

    public static int findBoardSize2 (ArrayList<Coordinates> input) {
        int result = 0;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).startX > result) {
                result = input.get(i).startX;
            }
            if (input.get(i).startY > result) {
                result = input.get(i).startY;
            }
            if (input.get(i).endX > result) {
                result = input.get(i).endX;
            }
            if (input.get(i).endY > result) {
                result = input.get(i).endY;
            }
        }
        /*
        input.stream()
                .map()
        */
        return result+1;
    }

    public static int findBoardSize (String[] input) {
        int result = 0;
        for (String line : input) {
            System.out.println(line);
            line = line.replace(" -> ", ",");
            System.out.println(line);
            String[] stringNums = line.split(",");
            for (String stringNum : stringNums) {
                if (result < Integer.parseInt(stringNum)) {
                    result = Integer.parseInt(stringNum);
                }
            }
        }
        return result + 1;
    }

    public static ArrayList<Coordinates> parseInput (String path) {

        ArrayList<Coordinates> result = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));

            for (int i = 0; i < allLines.size(); i++) {

                Pattern regex = Pattern.compile("^([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)$");
                Matcher matcher = regex.matcher(allLines.get(i));

                if (matcher.find()) {
                    System.out.println(matcher.group(0));
                    result.add(new Coordinates(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
                    System.out.println("New Coords: " + result.get(i).startX + "," + result.get(i).startY + " | " + result.get(i).endX + "," + result.get(i).endY);
                } else {
                    System.out.println(allLines.get(i) + " Mwa-mwaa");
                }
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void displayField (int[][] field) {
        for (int[] line : field) {
            for (int cell : line) {
                System.out.print(cell + " , ");
            }
            System.out.println("");
        }
    }

    public static int[][] generateField (int size) {
        int[][] field = new int[size][size];

        return field;
    }
}

class Coordinates {
    int startX = 0;
    int startY = 0;
    int endX = 0;
    int endY = 0;

    public Coordinates(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}