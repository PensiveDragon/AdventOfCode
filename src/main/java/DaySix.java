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

        ArrayList<lanternFishSchool> lanternFishSchools = parseInput(inputString);
        displayAllBracketCounts(lanternFishSchools);

        iterateTimeOneDay(lanternFishSchools);
    }

    public static ArrayList<lanternFishSchool> iterateTimeOneDay (ArrayList<lanternFishSchool> lanternFishSchools) {

        int school0value = lanternFishSchools.get(0).bracket_population;

        for (int schoolNum = 0; schoolNum < 7; schoolNum++) {
            lanternFishSchools.get(schoolNum).bracket_population = lanternFishSchools.get(schoolNum+1).bracket_population;
        }

        // calculateSchool0Effects

        displayAllBracketCounts(lanternFishSchools);

        return lanternFishSchools;
    }

    public static void displayAllBracketCounts (ArrayList<lanternFishSchool> lanternFishSchools) {
        System.out.println("Lantern Fish Schools Summary:");
        for (int i = 0; i < lanternFishSchools.size(); i++) {
            System.out.println(lanternFishSchools.get(i).bracket_name + " | " + lanternFishSchools.get(i).bracket_population);
        }
        System.out.println();
    }

    public static ArrayList<lanternFishSchool> setUpEvolutionTracker() {
        ArrayList<lanternFishSchool> evolutionTracker = new ArrayList<lanternFishSchool>(8);
        evolutionTracker.add(new lanternFishSchool("0", 0));
        evolutionTracker.add(new lanternFishSchool("1", 0));
        evolutionTracker.add(new lanternFishSchool("2", 0));
        evolutionTracker.add(new lanternFishSchool("3", 0));
        evolutionTracker.add(new lanternFishSchool("4", 0));
        evolutionTracker.add(new lanternFishSchool("5", 0));
        evolutionTracker.add(new lanternFishSchool("6", 0));
        evolutionTracker.add(new lanternFishSchool("7", 0));
        evolutionTracker.add(new lanternFishSchool("8", 0));

        //System.out.println(evolutionTracker.get(8).bracket_name);

        return evolutionTracker;
    }

    public static ArrayList<lanternFishSchool> parseInput(String inputLocation) {
        ArrayList<lanternFishSchool> lanternFishSchools = setUpEvolutionTracker();

        ArrayList<String> nearbyFishAssessment = new ArrayList<>();

        try {
            List<String> input = Files.readAllLines(Paths.get(inputLocation));

            for (int i = 0; i < input.size(); i++) {
                nearbyFishAssessment = new ArrayList<>(Arrays.asList(input.get(i).split(",")));
            }

            //System.out.println(lanternFishSchools.get(1).bracket_name + " | " + lanternFishSchools.get(1).bracket_population);

            for (String num : nearbyFishAssessment) {
                //System.out.println(num);
                switch (num) {
                    case "0":
                        lanternFishSchools.get(0).bracket_population++;
                        //System.out.println(lanternFishSchools.get(0).bracket_name + " | " + lanternFishSchools.get(0).bracket_population);
                        break;
                    case "1":
                        lanternFishSchools.get(1).bracket_population++;
                        //System.out.println(lanternFishSchools.get(1).bracket_name + " | " + lanternFishSchools.get(1).bracket_population);
                        break;
                    case "2":
                        lanternFishSchools.get(2).bracket_population++;
                        //System.out.println(lanternFishSchools.get(2).bracket_name + " | " + lanternFishSchools.get(2).bracket_population);
                        break;
                    case "3":
                        lanternFishSchools.get(3).bracket_population++;
                        //System.out.println(lanternFishSchools.get(3).bracket_name + " | " + lanternFishSchools.get(3).bracket_population);
                        break;
                    case "4":
                        lanternFishSchools.get(4).bracket_population++;
                        //System.out.println(lanternFishSchools.get(4).bracket_name + " | " + lanternFishSchools.get(4).bracket_population);
                        break;
                    case "5":
                        lanternFishSchools.get(5).bracket_population++;
                        //System.out.println(lanternFishSchools.get(5).bracket_name + " | " + lanternFishSchools.get(5).bracket_population);
                        break;

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return lanternFishSchools;
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