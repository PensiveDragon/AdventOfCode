import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayNine {

    static String testInputPath = "src/main/resources/day9_test_input.txt";
    static String inputPath = "src/main/resources/day9_input.txt";

    public static void main(String args[]) {
        System.out.println("Moo");

        ArrayList<String> input = parseInput(testInputPath);

        mapCave(input);


        //CaveMap caveMap = new CaveMap(input);
        //caveMap.printMap();

    }

    public static ArrayList<CaveTile> mapCave(ArrayList<String> input) {

        //System.out.println(input.get(0).charAt(1));

        ArrayList<CaveTile> caveMap = new ArrayList<>();

        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(0).length(); col++) {
                //System.out.println(input.get(row).charAt(col));
                caveMap.add(new CaveTile(row, col, input.get(row).charAt(col), 0));
                System.out.println(caveMap.get(0).height);
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

    int row_coord;
    int col_coord;
    int height;
    int smoke_level;

    public CaveTile(int row_coord, int col_coord, int height, int smoke_level) {
        this.row_coord = row_coord;
        this.col_coord = col_coord;
        this.height = height;
        this.smoke_level = smoke_level;
    }
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