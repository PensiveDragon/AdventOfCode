import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DayFour {

    // Puzzle input: n*(5x5) boards = 3d array?
    // Puzzle input: Array(m) of drawn numbers (0-99)

    // Parse input data into both data structures
    // Cycle through drawn numbers. For each number:
    // - Check through each board, marking the found number in each (updating the found number in each to a *)?
    // - Check whether any board has won yet
    // - (2) Add board to won board list
    // - (2) Change board no's to -2.
    // On win, sum all unmarked numbers on winning board, multiply by last number drawn.

    // Check if this was the last board solved

    static int board;
    static HashSet<Integer> solvedSet = new HashSet<>();
    static HashSet<Integer> bingoSet = new HashSet<>();

    public static void main (String args[]) {
        System.out.println("moo");



        String[] input = readInTextFile();
        ArrayList<String> drawnNumbers = processInputToDrawnNumbers(input);
        int[][][] bingoBoardArray = processInputToBingoBoardArray(input);

        //displayAllBingoBoards(bingoBoardArray);

        //cycleThroughDrawnNumbers(drawnNumbers, bingoBoardArray);
        cycleThroughDrawnNumbersPartTwo(drawnNumbers, bingoBoardArray);


        //bingoBoardArray = updateNumberOnAllBoards(0, bingoBoardArray);
        //updateNumberOnAllBoards(0, bingoBoardArray);

        //checkForAnyCompleteBoard(bingoBoardArray);

        //drawnNumbers.forEach(System.out::println);

        /*
        int[][] testGrid = testGridSetup();
        int[][][] testBoard = new int[1][5][5];
        testBoard[0] = testGrid;
        checkForAnyCompleteBoard(testBoard);
        */

        System.out.println(solvedSet);
    }


    public static int[][] amendCompletedBoard (int[][] completedBoard) {
        System.out.println("Board " + (board + 1) + " is completed, amending numbers.");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                completedBoard[i][j] = -2;
            }
        }
        return completedBoard;
    }

    public static void checkForUnsolvedBoards (int[][][] bingoBoardArray) {
        System.out.println("Number of boards completed: " + solvedSet.size());
        System.out.println("Number of bingoNumbers drawn: " + bingoSet.size());
    }

    public static void addBoardNumberToSolvedList (int boardNumber) {
        solvedSet.add(boardNumber);
        System.out.println("Adding Board No " + boardNumber + " to Solved List. Solved set size is: " + solvedSet.size());
        if (solvedSet.size() == 100) {
            //System.out.println("Final board number is: " + boardNumber);
        }
    }

    public static void solvePuzzle(int[][] winningBingoBoard, int lastNumberDrawn) {
        int sum = 0;
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (winningBingoBoard[row][column] > -1) {
                    sum += winningBingoBoard[row][column];
                    System.out.println("Winning board sum... " + sum);
                }
            }
        }

        System.out.println("Winning board sum * last number drawn = " + (sum*lastNumberDrawn));
    }

    public static int[][] testGridSetup() {
        int[][] testGrid = new int[5][5];

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (row == 4) {
                    testGrid[row][column] = -1;
                } else if (column == 1) {
                    testGrid[row][column] = -1;
                } else {
                    testGrid[row][column] = row + column;
                }
            }
        }
        System.out.println("Test Grid Setup Complete");
        return testGrid;
    }

    public static boolean checkForCompleteColumn(int[][] bingoBoardArrayColumn, int column) {
        //System.out.println("Check for Complete Column");
        int matchCount = 0;
        for (int row = 0; row < 5; row++) {
            //System.out.println(bingoBoardArrayColumn[row][column]);
            if (bingoBoardArrayColumn[row][column] == -1) {
                matchCount++;
                //System.out.println("Match Count: " + matchCount);
            }
            if (matchCount == 5) {
                System.out.println("Found Complete Column!");
                return true;
            }
        }
        return false;
    }

    public static boolean checkForCompleteRow(int[] bingoBoardArrayRow) {
        //System.out.println("Check for Complete Row");
        int matchCount = 0;
        for (int column = 0; column < 5; column++) {
            //System.out.println(bingoBoardArrayRow[column]);
            if (bingoBoardArrayRow[column] == -1) {
                matchCount++;
            }
            if (matchCount == 5) {
                System.out.println("Found Complete Row!");
                return true;
            }
        }
        return false;
    }

    public static boolean checkForAnyCompleteBoard(int[][][] bingoBoardArray, int number) {
        // check each board
        boolean result = false;
        for (board = 0; board < bingoBoardArray.length; board++) {
            /*
            if (bingoBoardArray[board][0][0] == -2) {
                continue;
            }*/
            if (solvedSet.contains(board)) {
                continue;
            }
            //System.out.println("Checking board " + board);
            // check each row
            for (int row = 0; row < 5; row++) {
                if (checkForCompleteRow(bingoBoardArray[board][row])) {
                    System.out.println("Found Complete Board - " + board);
                    addBoardNumberToSolvedList(board);

                    solvePuzzle(bingoBoardArray[board], number);

                    //bingoBoardArray[board] = amendCompletedBoard(bingoBoardArray[board]);
                    System.out.println("COMPLETE BOARD FOUND! - Board No. " + board + " Last Drawn Number: " + number);
                    result = true;
                }
            }
            // check each column
            for (int column = 0; column < 5; column++) {
                if (checkForCompleteColumn(bingoBoardArray[board], column)) {
                    System.out.println("Found Complete Board - " + board);
                    addBoardNumberToSolvedList(board);

                    solvePuzzle(bingoBoardArray[board], number);

                    //bingoBoardArray[board] = amendCompletedBoard(bingoBoardArray[board]);
                    System.out.println("COMPLETE BOARD FOUND! - Board No. " + board + " Last Drawn Number: " + number);
                    result = true;
                }
            }
        }
        return result;
    }

    public static int[][][] updateNumberOnAllBoards(int number, int[][][] bingoBoardArray) {
        int[][][] result = bingoBoardArray;
        int matchCounter = 0;
        System.out.println("Replacing all " + number + " with -1");

        for (int board = 0; board < 100; board++) {
            for (int column = 0; column < 5; column++) {
                for (int row = 0; row < 5; row++) {
                    //System.out.println(result[board][column][row]);
                    if (result[board][column][row] == number) {
                        //System.out.println("MATCH " + result[board][column][row]);
                        //row = -1;
                        result[board][column][row] = -1;
                        //System.out.println(result[board][column][row]);
                        matchCounter++;
                    }
                }
            }
        }

        System.out.println("Number of matches for number (" + number + ") = " + matchCounter);
        return result;
    }

    public static void cycleThroughDrawnNumbers(ArrayList<String> drawnNumbers, int[][][] bingoBoardArray) {
        for (String number : drawnNumbers) {
            //System.out.println(number);
            bingoBoardArray = updateNumberOnAllBoards(Integer.parseInt(number), bingoBoardArray);
            if (checkForAnyCompleteBoard(bingoBoardArray, Integer.parseInt(number))) {
                System.out.println("COMPLETE BOARD FOUND! - Board No. " + board + " Last Drawn Number: " + number);
                displaySingleBingoBoard(bingoBoardArray[board]);
                solvePuzzle(bingoBoardArray[board], Integer.parseInt(number));
                break;
            }
        }
    }

    public static void cycleThroughDrawnNumbersPartTwo(ArrayList<String> drawnNumbers, int[][][] bingoBoardArray) {
        for (String number : drawnNumbers) {

            System.out.println("Drawn Number: " + number);
            if (solvedSet.size() == 99) {
                //stop the presses
                System.out.println("This is the final board!");
                System.out.println("Number of finished boards: " + solvedSet.size());
            }

            if (solvedSet.size() == 100)

            bingoSet.add(Integer.valueOf(number));
            //System.out.println(number);
            bingoBoardArray = updateNumberOnAllBoards(Integer.parseInt(number), bingoBoardArray);
            if (checkForAnyCompleteBoard(bingoBoardArray, Integer.parseInt(number))) {

                checkForUnsolvedBoards(bingoBoardArray);
                displayAllBingoBoards(bingoBoardArray);
            }
        }
    }

    public static void displaySingleBingoLine(int[] input) {
        String line = "";
        for (int num : input) {
            if (num < 10) {
                line += " ";
            }
            line += num;
            line += " ";
        }
        System.out.println(line);
    }

    public static void displaySingleBingoBoard(int[][] input) {
        for (int rowNo = 0; rowNo < input.length; rowNo++) {
            //System.out.println("Row: " + rowNo);
            displaySingleBingoLine(input[rowNo]);
        }
    }

    public static void displayAllBingoBoards(int[][][] input) {
        for (int boardNo = 0; boardNo < input.length; boardNo++) {
            System.out.println("Board " + (boardNo+1));
            displaySingleBingoBoard(input[boardNo]);
            System.out.println();
        }
    }

    public static int[][][] processInputToBingoBoardArray(String[] input) {

        int[][][] result = new int[input.length/6][5][5];
        System.out.println("Bingo Board Array dimensions: " + result.length + "|" + result[0].length + "|" + result[0][0].length);

        int board = -1, row = 0, column = 0;

        for (String line : input) {

            if (line.length() == 0) {
                //System.out.println("New Board");
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
}
