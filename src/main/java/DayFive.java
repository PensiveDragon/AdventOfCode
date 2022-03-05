public class DayFive {

    // Parse List input
    // Create X,X size Grid
    // For each line of input
    // -> Use coords to update grid with lines
    // -> Each line adds 1 to the coords listed
    // Count number of coords >1

    public static void main(String[] args) {
        System.out.println("Moo");

        System.out.println("Boop");

        int[][] field = generateField(10);
        displayField(field);
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
