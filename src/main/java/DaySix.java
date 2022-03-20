import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaySix {

    // Parse input
    // Create a list of numbers populated by different quantities of fish
    // Loop for x
    // > Method iterating fish down ranks of values
    // > Fish at value 0 reset to 6 and create new fish at 8

    public static void main(String args[]) {
        System.out.println("Moo");

        String inputString = "src/main/resources/day6_input.txt";

        parseInput(inputString);
    }

    public static ArrayList<lanternFishSchool> setUpEvolutionTracker() {
        ArrayList<lanternFishSchool> evolutionTracker = new ArrayList<lanternFishSchool>(8);
        evolutionTracker.add(new lanternFishSchool("zero", 0));
        evolutionTracker.add(new lanternFishSchool("one", 1));
        evolutionTracker.add(new lanternFishSchool("two", 2));
        evolutionTracker.add(new lanternFishSchool("three", 3));
        evolutionTracker.add(new lanternFishSchool("four", 4));
        evolutionTracker.add(new lanternFishSchool("five", 5));
        evolutionTracker.add(new lanternFishSchool("six", 6));
        evolutionTracker.add(new lanternFishSchool("seven", 7));
        evolutionTracker.add(new lanternFishSchool("eight", 8));

        return evolutionTracker;
    }

    public static ArrayList<lanternFishSchool> parseInput(String inputLocation) {
        ArrayList<lanternFishSchool> lanternFishSchools = setUpEvolutionTracker();

        ArrayList<String> drawnNumbersList = new ArrayList<>();

        try {
            List<String> input = Files.readAllLines(Paths.get(inputLocation));

            for (int i = 0; i < input.size(); i++) {
                drawnNumbersList = new ArrayList<>(Arrays.asList(input.get(i).split(",")));
            }

            for (String num : drawnNumbersList) {
                System.out.println(num);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return evolutionTracker;
    }

    public static ArrayList<Coordinates> parseInput2(String path) {

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
}

class lanternFishSchool {
    String bracket_name = "";
    int bracket_population = 0;

    public lanternFishSchool(String bracket_name, int bracket_population) {
        this.bracket_name = bracket_name;
        this.bracket_population = bracket_population;
    }
}