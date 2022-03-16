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
        String inputPath = "src/main/resources/day5_input.txt";
        ArrayList<Coordinates> input = parseInput(inputPath);

        int[][] field = updateFieldFromCoordinates(input);
        displayField(field);
        countNumberOfOverlapsInField(field);
    }

    public static void countNumberOfOverlapsInField (int[][] field) {
        int overlapCount = 0;
        for (int row = 0; row < field[0].length; row++) {
            for (int col = 0; col < field.length; col ++) {
                if (field[row][col] > 1) {
                    overlapCount++;
                }
            }
        }
        System.out.println("The number of overlaps is: " + overlapCount);
    }

    public static int[][] updateFieldFromCoordinates (ArrayList<Coordinates> coordinatesArrayList) {
        int[][] field = generateField(findBoardSize(coordinatesArrayList));

        for (Coordinates coordinates : coordinatesArrayList) {
            coordinates.markOnField(field);
        }
        return field;
    }

    public static int findBoardSize (ArrayList<Coordinates> input) {
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

                System.out.print(String.format("%4d", cell));

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

    public boolean isDiagonal () {
        if (isVertical() | isHorizontal()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isHorizontal() {
        return startY == endY;
    }

    private boolean isVertical() {
        return startX == endX;
    }

    public void markOnField (int[][] field) {
        if (isDiagonal()) {
            //System.out.println("Is diagonal - ignoring");
            /*
            int minX = Math.min(startX, endX);
            int maxX = Math.max(startX, endX);
            boolean upDiagonal = true;
            if (startY < endY) {
                upDiagonal = false;
            }
            for (int x = minX; x <= maxX; x++) {
                field[startY][x]++;
            }
            */

            // Assumption is that the diagonals travel down and right, include flags to determine if either are flipped

            int minX = Math.min(startX, endX);
            int maxX = Math.max(startX, endX);

            for (int steps = minX; steps <= maxX; steps++) {

                field[startY][startX]++;
                if (startX <= endX) {
                    startX++;
                } else {
                    startX--;
                }
                if (startY <= endY) {
                    startY++;
                } else {
                    startY--;
                }
            }
/*
            int horizontalDirection = endX - startX;
            int verticalDirection = endY - startY;
            System.out.println();
            String diagDesc = "";
            if (horizontalDirection > 0) {
                diagDesc += "Right / ";
            } else {
                diagDesc += "Left / ";
            }
            if (verticalDirection > 0) {
                diagDesc += "Down";
            } else {
                diagDesc += "Up";
            }
            System.out.println(diagDesc + " (" + horizontalDirection + " | " + verticalDirection + ")");
*/

        } else if (isHorizontal()) {
            int minX = Math.min(startX, endX);
            int maxX = Math.max(startX, endX);
            for (int x = minX; x <= maxX; x++) {
                field[startY][x]++;
            }

        } else if (isVertical()){
            int minY = Math.min(startY, endY);
            int maxY = Math.max(startY, endY);
            for (int y = minY; y <= maxY; y++) {
                field[y][startX]++;
            }

        } else {
            throw new AssertionError("Should never get here!");
        }
    }
}