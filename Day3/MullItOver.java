import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class MullItOver {
    public static void main(String[] args) {
        // Path to the file
        String filePath = "input.txt"; 

        try {
            // Read the string from the file
            String brokenString = readFile(filePath);

            // Extract all "mul(x, y)" expressions
            List<Integer> results = extractAndCalculate(brokenString);

            // Calculate sums for part 1 and part 2
            int sumPart1 = results.stream().reduce(0, Integer::sum); // Total sum
            int sumPart2 = results.size(); // Number of "mul" expressions

            // Output the results
            System.out.println("Result for part 1: " + sumPart1);
            System.out.println("Result for part 2: " + sumPart2);

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

    // Function to extract and calculate "mul(x, y)" expressions
    private static List<Integer> extractAndCalculate(String input) {
        List<Integer> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1)); // First number
            int y = Integer.parseInt(matcher.group(2)); // Second number
            results.add(x * y); // Calculate and add the result to the list
        }

        return results;
    }
}