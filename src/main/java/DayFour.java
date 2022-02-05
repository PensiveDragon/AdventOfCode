import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFour {

    // Puzzle input: n*(5x5) boards = 3d array?
    // Puzzle input: Array(m) of drawn numbers (0-99)

    // Parse input data into both data structures
    // Cycle through drawn numbers. For each number:
    // - Check through each board, marking the found number in each (updating the found number in each to a *)?
    // - Check whether any board has won yet
    // On win, sum all unmarked numbers on winning board, multiply by last number drawn.

    public static void main (String args[]) {
        System.out.println("moo");

        String[] input = readInTextFile();
        ArrayList<String> drawnNumbers = processInputToDrawnNumbers(input);
        int[][][] bingoBoardArray = processInputToBingoBoardArray(input);



        //drawnNumbers.forEach(System.out::println);

    }

    public static void displaySingleBingoBoard(int[][] input) {
        //System.out.println("Meow");
        for (int column = 0; column < 5; column++) {
            String line = "";
            for (int row = 0; row < 5; row++) {
                //System.out.println(input[column][row]);
                if (input[column][row] < 10) {
                    line += " ";
                }
                line += input[column][row];
                line += " ";
            }
            System.out.println(line);
        }
    }

    public static int[][][] processInputToBingoBoardArray(String[] input) {

        int[][][] result = new int[input.length/6][5][5];
        System.out.println("Bingo Board Array dimensions: " + result.length + "|" + result[0].length + "|" + result[0][0].length);

        int board = -1, row = 0, column = 0;

        for (String line : input) {

            if (line.length() == 0) {
                System.out.println("New Board");
                board++;
                row = 0;

            } else if (line.length() < 20) {

                line = line.trim();
                line = line.replace("  ", " ");
                String[] splitString = line.split(" ");
                for (column = 0; column < 5; column++) {
                    //System.out.println("Inspecting: '" + splitString[column] + "'");
                    result[board][row][column] = Integer.parseInt(splitString[column]);
                    //System.out.println("Board " + board + " Row " + row + " Column " + column);
                }
                row++;
                /*
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        row = r+1;
                        column = c+1;
                        System.out.println("Board " + board + " Row " + row + " Column " + column);
                        //result[board][row][column] = 0;
                    }
                }*/


                displaySingleBingoBoard(result[board]);
            }
        }

        return result;
    }

    public static ArrayList<String> processInputToDrawnNumbers(String[] input) {

        String drawnNumbersString = "";
        ArrayList<String> drawnNumbersList = new ArrayList<>();

        for (String line : input) {
            if (line.length() > 20) {
               drawnNumbersString = line;
               drawnNumbersList = new ArrayList<>(Arrays.asList(drawnNumbersString.split(",")));
            }
        }

        return drawnNumbersList;
    }

    public static String[] readInTextFile() {

        String[] allInput;

        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/day4_input.txt"));
            allInput = allLines.toArray(new String[0]);
            //System.out.println(allInput.length);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public class GameBoard {
        int row = 5;
        int column = 5;
        int[][] grid = new int[row][column];

    }
}
