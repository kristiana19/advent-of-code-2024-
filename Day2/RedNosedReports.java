import java.util.*;

public class RedNosedReports {
    public static void main(String[] args) {
        
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Instructions for the user
        System.out.println("Enter numbers in one line (separated by spaces), type 'konec' to finish.");

        int safeReportsCount = 0;

        while (true) {
            // Read a line from the user
            String report = scanner.nextLine();

            // Check if the input is 'konec', if yes, break the loop
            if (report.equalsIgnoreCase("konec")) {
                break;
            }

            // Check if the report is safe
            if (isSafe(report)) {
                safeReportsCount++;
            }
        }

        // Output the number of safe reports
        System.out.println("The number of safe reports is: " + safeReportsCount);
        
        scanner.close();
    }

    public static boolean isSafe(String report) {
        // Split the input string into individual numbers
        String[] levelStrings = report.split(" ");
        int[] levels = new int[levelStrings.length];

        // Convert the string array into an integer array
        for (int i = 0; i < levelStrings.length; i++) {
            levels[i] = Integer.parseInt(levelStrings[i]);
        }

        boolean ascending = true;
        boolean descending = true;

        for (int i = 1; i < levels.length; i++) {
            int difference = levels[i] - levels[i - 1];

            // Check if the difference is within the allowed range (-3 to 3)
            if (Math.abs(difference) < 1 || Math.abs(difference) > 3) {
                return false;
            }

            // Check whether the levels are strictly ascending or descending
            if (difference > 0) {
                descending = false;
            } else if (difference < 0) {
                ascending = false;
            }
        }

        // The report is safe if all levels are ascending or all are descending
        return descending || ascending;
    }
}