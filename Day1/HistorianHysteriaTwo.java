import java.util.*;

public class HistorianHysteriaTwo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating lists
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // Input of numbers for the left and right lists
        System.out.println("Enter numbers for the left and right lists in two columns (enter 0 0 to finish):");

        while (true) {
            // Input of two numbers (one for the left list, one for the right list)
            int leftNumber = scanner.nextInt();
            int rightNumber = scanner.nextInt();

            // Stop the input if both numbers are 0
            if (leftNumber == 0 && rightNumber == 0) {
                break;
            }

            // Adding numbers to the appropriate lists
            leftList.add(leftNumber);
            rightList.add(rightNumber);
        }

        // Calculating the similarity
        int similarityResult = 0;
        for (Integer number : leftList) {
            // Counts how many times a number from the left list appears in the right list
            int occurrenceCount = Collections.frequency(rightList, number);
            similarityResult += number * occurrenceCount;
        }

        // Printing the result
        System.out.println("The total similarity between the lists is: " + similarityResult);

        scanner.close();
    }
}