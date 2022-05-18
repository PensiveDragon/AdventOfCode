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

        CaveMap caveMap = new CaveMap(input);
        caveMap.printMap(caveMap.caveMap[0][0]);

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

class CaveMap {

    String[][] caveMap;
    //int num = 1;

    public CaveMap(ArrayList<String> input) {
        int x = input.size();
        int y = input.get(0).length();
        caveMap = new String[x][y];


    }

    public void printMap() {

        System.out.println(caveMap[0][0]);
        /*
        for (String[] row : caveMap) {
            for (String cell : row) {
                System.out.println(cell);
                System.out.println();
                //System.out.println(num);
            }
        }*/
    }
}