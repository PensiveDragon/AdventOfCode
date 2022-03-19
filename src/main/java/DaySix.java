import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    }

    public static LinkedHashMap<String, Integer> setUpEvolutionTracker() {
        LinkedHashMap<String, Integer> evolutionTracker = new LinkedHashMap<String, Integer>();
        evolutionTracker.put("internalKeyZero", 0);
        evolutionTracker.put("internalKeyOne", 0);
        evolutionTracker.put("internalKeyTwo", 0);
        evolutionTracker.put("internalKeyThree", 0);
        evolutionTracker.put("internalKeyFour", 0);
        evolutionTracker.put("internalKeyFive", 0);
        evolutionTracker.put("internalKeySix", 0);
        evolutionTracker.put("internalKeySeven", 0);
        evolutionTracker.put("internalKeyEight", 0);

        return evolutionTracker;
    }

    public static LinkedHashMap<String, Integer> parseInput(String inputLocation) {
        LinkedHashMap<String, Integer> evolutionTracker = setUpEvolutionTracker();



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
