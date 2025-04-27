import java.util.*;

public class HistorianHysteria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create dynamic lists for input elements
        List<Integer> leftList = new ArrayList<>(); // List for the left collection
        List<Integer> rightList = new ArrayList<>(); // List for the right collection

        System.out.println("Enter elements in the format: 'left right'. To finish input, press Enter on an empty line:");

        // Reading input line by line
        while (sc.hasNextLine()) { // Check if there are more lines to read
            String line = sc.nextLine().trim(); // Read the current line and remove whitespace from the ends
            if (line.isEmpty()) { // If the line is empty, stop the input
                break; // Exit the loop
            }

            String[] numbers = line.split("\\s+"); // Split the line into parts by whitespace
            if (numbers.length == 2) { // Check if exactly two numbers were entered
                leftList.add(Integer.parseInt(numbers[0])); // Add the first number to the left list
                rightList.add(Integer.parseInt(numbers[1])); // Add the second number to the right list
            } else { // If the line does not contain exactly two numbers, display an error message
                System.out.println("Invalid input! Please enter two numbers separated by a space.");
            }
        }

        // Convert dynamic lists into arrays
        int[] left = leftList.stream().mapToInt(Integer::intValue).toArray(); // Convert the left list to an array
        int[] right = rightList.stream().mapToInt(Integer::intValue).toArray(); // Convert the right list to an array

        // Calculate the distance between the two collections
        int distance = calculateDistance(left, right); // Call the function to calculate the distance

        // Print the result
        System.out.println("The total distance between the collections is: " + distance); // Display the total distance
    }

    // Function to calculate the distance between two collections
    public static int calculateDistance(int[] left, int[] right) {
        Arrays.sort(left); // Sort the left collection in ascending order
        Arrays.sort(right); // Sort the right collection in ascending order

        int distance = 0; // Initialize the variable to store the total distance
        for (int i = 0; i < left.length; i++) { // Iterate through the elements of both collections
            distance += Math.abs(left[i] - right[i]); // Add the absolute difference of corresponding elements
        }

        return distance; // Return the total distance
    }
}