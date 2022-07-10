import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DayNine {

    static String testInputPath = "src/main/resources/day9_test_input.txt";
    static String inputPath = "src/main/resources/day9_input.txt";

    // Part 2
    // Find the 3 largest basins
    // A basin is any orthogonal collection of numbers that are <9
    // Iterate Board
    // - On finding a number <9 check above
    // ->

    public static void main(String args[]) {
        System.out.println("Moo");

        ArrayList<String> input = parseInput(testInputPath);

        ArrayList<CaveTile> caveMap = mapCave(input);

        //ArrayList<Integer> lowestPoints = findLowestPointsFromArrayList(caveMap, input);

        //System.out.println(lowestPoints);

        //System.out.println("Total risk level is: " + convertLowestPointsToTotalRiskLevel(lowestPoints, caveMap));

        //findColumnCount(caveMap);
        //findRowCount(caveMap);
        iterateThroughCaveMap(caveMap);

    }

    public static int findRowCount(ArrayList<CaveTile> caveMap) {

        int columnCount = findColumnCount(caveMap);
        int result = caveMap.size() / columnCount;
        //System.out.println("Row count = " + result);

        return result;
    }

    public static int findColumnCount(ArrayList<CaveTile> caveMap) {

        int result = -1;

        for (int i = 0; i < caveMap.size(); i++) {
            if (i > 0) {
                if (caveMap.get(i).getX() > caveMap.get(i+1).getX()) {
                    result = caveMap.get(i).getX() + 1;

                    //System.out.println("Column count = " + result);

                    return result;
                }
            }
        }
        return result;
    }

    public static int checkNorthIsInBounds(ArrayList<CaveTile> caveMap, int index) {
        int northIndex = -1;

        if ((caveMap.get(index).y - findColumnCount(caveMap)) > 0) {
            northIndex = index - findColumnCount(caveMap);
        }

        return northIndex;
    }

    public static int checkEastIsInBounds(ArrayList<CaveTile> caveMap, int index) {
        int eastIndex = -1;

        if (((caveMap.get(index).getX() + 1) < findColumnCount(caveMap))) {
            eastIndex = index + 1;
        }

        return eastIndex;
    }

    public static int checkSouthIsInBounds(ArrayList<CaveTile> caveMap, int index) {
        int southIndex = -1;

        if (index + findColumnCount(caveMap) < caveMap.size()) {
            southIndex = index + findColumnCount(caveMap);
        }

        return southIndex;
    }

    public static int checkWestIsInBounds(ArrayList<CaveTile> caveMap, int index) {
        int westIndex = -1;

        if ((caveMap.get(index).getX()) % findColumnCount(caveMap) != 0) {
            westIndex = index - 1;
        }

        return westIndex;
    }

    public static Set<Integer> mapBasin (ArrayList<CaveTile> caveMap, int index, Set<Integer> indexesInBasin) { //recursive method to scan area.

        //is current tile valid (ie. <9, unscanned, inbounds)

        if (index != -1 && !caveMap.get(index).isMapped() && caveMap.get(index).getHeight() < 9) { //reasons for this to execute.

            System.out.println("Investigating tile index " + index + " (no. " + caveMap.get(index).getHeight() + ")");

            caveMap.get(index).setMapped(true); //set mapped = true

            indexesInBasin.add(index);

            mapBasin(caveMap, checkNorthIsInBounds(caveMap, index), indexesInBasin);
            mapBasin(caveMap, checkEastIsInBounds(caveMap, index), indexesInBasin);
            mapBasin(caveMap, checkSouthIsInBounds(caveMap, index), indexesInBasin);
            mapBasin(caveMap, checkWestIsInBounds(caveMap, index), indexesInBasin);
            //System.out.println("Stuff happened.");
        }

        return indexesInBasin;
    }

    public static boolean isThisUnmapped (CaveTile caveTile) {
        if (!caveTile.mapped) {
            return true;
        }
        return false;
    }

    public static boolean isThisInABasin (CaveTile caveTile) {
        if (caveTile.getHeight() < 9) {
            return true;
        }
        return false;
    }

    public static void iterateThroughCaveMap (ArrayList<CaveTile> caveMap) {

        int[] top3basins = new int[3];

        int index = 0;
        System.out.println("");
        for (CaveTile tile : caveMap) {
            if (isThisInABasin(tile) & isThisUnmapped(tile)) {
                Set<Integer> indexesInBasin = new HashSet<>();
                Set<Integer> tilesInBasin = mapBasin(caveMap, index, indexesInBasin);
                System.out.println("Tiles in Basin: " + tilesInBasin.size());
                if (tilesInBasin.size() > top3basins[0]) {
                    top3basins[2] = top3basins[1];
                    top3basins[1] = top3basins[0];
                    top3basins[0] = tilesInBasin.size();
                } else if (tilesInBasin.size() > top3basins[1]) {
                    top3basins[2] = top3basins[1];
                    top3basins[1] = tilesInBasin.size();
                } else if (tilesInBasin.size() > top3basins[2]) {
                    top3basins[2] = tilesInBasin.size();
                }
            }
            index++;
        }

        System.out.println(Arrays.toString(top3basins));
        System.out.println("Top 3 Basin size multiple: " + top3basins[0]*top3basins[1]*top3basins[2]);
    }

    public static int convertLowestPointsToTotalRiskLevel (ArrayList<Integer> lowestPoints, ArrayList<CaveTile> caveMap) {

        int riskLevel = 0;
        int index = 0;

        for (int lowestPoint : lowestPoints) {
            System.out.println("Index: " + index++ + " Risk Level: " + (caveMap.get(lowestPoint).getHeight()+1));
            riskLevel+= (caveMap.get(lowestPoint).getHeight()+1);
        }

        return riskLevel;
    }

    public static ArrayList<Integer> findLowestPointsFromArrayList(ArrayList<CaveTile> caveMap, ArrayList<String> input) {
        // check through each item
        // > each item runs its checkIfLowest method
        // > if lowest, add coords to list
        // return list
/*
        int max_row = caveMap.size();
        int max_col = caveMap.get(0).toString().length();

 */

        int max_row = input.size();
        System.out.println("max_row: " + input.size());

        int max_col = input.get(0).toString().length();
        System.out.println("max_col: " + input.get(0).toString().length());


        ArrayList<Integer> low_point_indexes = new ArrayList<>();

        for (int index = 0; index < caveMap.size(); index++) {
            if (checkIfLowest(caveMap, max_row, max_col, index)) {
                //add index to list
                low_point_indexes.add(index);
            };
        }
        return low_point_indexes;
    }

    public static boolean checkIfLowest(ArrayList<CaveTile> caveMap, int max_row, int max_col, int index) {
        boolean isLowest = true;
        int currentHeight = caveMap.get(index).getHeight();
        //System.out.println("index: " + index + ", max_row: " + max_row + ", max_col: " + max_col);

        if (index % max_col > 0) {
            // check current square against left square
            if (caveMap.get(index).getHeight() >= caveMap.get(index-1).getHeight()) {
                // if current square is > return false
                isLowest = false;
            }
        }

        if (index - max_col >= 0) {
            // check current square against above square
            if (caveMap.get(index).getHeight() >= caveMap.get(index - max_col).getHeight()) {
                // if current square is > return false
                isLowest = false;
            }
        }

        if (index % max_col < max_col - 1) {
            // check current square against right square
            if (caveMap.get(index).getHeight() >= caveMap.get(index+1).getHeight()) {
                // if current square is > return false
                isLowest = false;
            }
        }

        if (index <= max_col * (max_row-1) - 1) {
            // check current square against right square
            if (caveMap.get(index).getHeight() >= caveMap.get(index + max_col).getHeight()) {
                // if current square is > return false
                isLowest = false;
            }
        }

        return isLowest;
    }

    public static ArrayList<CaveTile> mapCave(ArrayList<String> input) { //Takes raw file input and outputs

        System.out.println("Mapping Cave...");

        //System.out.println(input.get(0).charAt(1));

        ArrayList<CaveTile> caveMap = new ArrayList<>();

        int index = 0;
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(0).length(); col++) {
                //System.out.println(input.get(row).charAt(col));
                caveMap.add(new CaveTile(row, col, Integer.parseInt(String.valueOf(input.get(row).charAt(col)))));
                //System.out.println(caveMap.get(index).height);
                index++;
            }
        }

        return caveMap;
    }

    public static ArrayList<String> parseInput(String path) {

        ArrayList<String> allInput = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));

            allInput.addAll(allLines);
            //System.out.println(allInput);
            System.out.println("Read in " + allInput.size() + " lines.");

            System.out.println(allInput);

            return allInput;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

class CaveTile {

    int y;
    int x;
    int height;
    boolean mapped = false;

    public CaveTile(int y, int x, int height) {
        this.y = y;
        this.x = x;
        this.height = height;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMapped() { return mapped; }

    public void setMapped(boolean mapped) { this.mapped = mapped; }
}

class CaveMap {

    String[][] caveMap;
    //int num = 1;

    public CaveMap(ArrayList<String> input) {
        int x = input.size();
        int y = input.get(0).length();
        caveMap = new String[x][y];

        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                caveMap[col][row] = "0";
            }
        }
    }

    public void printMap() {

        System.out.println(caveMap);

        for (String[] row : caveMap) {
            for (String cell : row) {
                System.out.println(cell);
            }
        }
    }

    public static void displayField (int[][] field) {
        for (int[] line : field) {
            for (int cell : line) {

                System.out.print(String.format("%4d", cell));

            }
            System.out.println("");
        }
    }
}