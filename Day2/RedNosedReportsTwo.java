import java.util.*;

public class RedNosedReportsTwo {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Instructions for the user
        System.out.println("Enter numbers in one line (separated by spaces), type 'konec' to finish.");

        int safeReportsCount = 0;

        while (true) {
            
            String report = scanner.nextLine();

            // Check if the input is 'konec', if yes, break the loop
            if (report.equalsIgnoreCase("konec")) {
                break;
            }

            // Check if the report is safe considering the Problem Dampener
            if (isSafeWithProblemDampener(report)) {
                safeReportsCount++;
            }
        }

        // Output the number of safe reports
        System.out.println("The number of safe reports is: " + safeReportsCount);
        
        scanner.close();
    }

    // Check if the report is safe
    public static boolean isSafeWithProblemDampener(String report) {
        // Split the input string into individual numbers
        String[] levelStrings = report.split(" ");
        int[] levels = new int[levelStrings.length];

        // Convert the string array into an integer array
        for (int i = 0; i < levelStrings.length; i++) {
            levels[i] = Integer.parseInt(levelStrings[i]);
        }

        // Check if the report is already safe without removing any levels
        if (isSafe(report)) {
            return true;
        }

        // If the report is not safe, check if it can be made safe by removing one level
        for (int i = 0; i < levels.length; i++) {
            // Create a new string without the i-th level
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < levels.length; j++) {
                if (j != i) {
                    sb.append(levels[j]).append(" ");
                }
            }

            // Check if the report is safe after removing the level
            if (isSafe(sb.toString().trim())) {
                return true;
            }
        }

        // If removing any level doesn't help, the report is not safe
        return false;
    }

    // Check if the report is safe (without removing any levels)
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