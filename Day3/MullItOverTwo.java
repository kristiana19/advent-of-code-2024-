import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class MullItOverTwo {
    public static void main(String[] args) {
        // Path to the file
        String filePath = "input2.txt"; 

        try {
            // Read the string from the file
            String brokenString = readFile(filePath);

            // Process the string with new instructions
            int total = processInstructions(brokenString);

            // Output the result
            System.out.println("Result: " + total);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    // Function to read a file
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    // Function to process the instructions
    private static int processInstructions(String input) {
        Pattern mulPattern = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");

        Matcher matcher;
        boolean enabled = true; // Initial state allows `mul` instructions
        int totalSum = 0;

        // Iterate through the input and search for instructions
        for (int i = 0; i < input.length(); ) {
            String subInput = input.substring(i);

            // Check for `do()` instruction
            matcher = doPattern.matcher(subInput);
            if (matcher.find() && matcher.start() == 0) {
                enabled = true;
                i += matcher.end();
                continue;
            }

            // Check for `don't()` instruction
            matcher = dontPattern.matcher(subInput);
            if (matcher.find() && matcher.start() == 0) {
                enabled = false;
                i += matcher.end();
                continue;
            }

            // Check for `mul(x, y)` instruction
            matcher = mulPattern.matcher(subInput);
            if (matcher.find() && matcher.start() == 0) {
                if (enabled) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    totalSum += x * y;
                }
                i += matcher.end();
                continue;
            }

            // If nothing is recognized, move to the next character
            i++;
        }

        return totalSum;
    }
}