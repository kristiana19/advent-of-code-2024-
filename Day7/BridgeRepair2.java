import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BridgeRepair2 {

    // Function to calculate all possible results using three operators (+, *, ||)
    public static long calculate(String equation) {
        String[] parts = equation.split(": ");
        long target = Long.parseLong(parts[0]);
        String[] numbers = parts[1].split(" ");
        
        // Check all possible combinations of operators (+, *, ||)
        List<Long> results = new ArrayList<>();
        results.add(Long.parseLong(numbers[0]));
        
        for (int i = 1; i < numbers.length; i++) {
            List<Long> newResults = new ArrayList<>();
            long num = Long.parseLong(numbers[i]);
            
            for (long res : results) {
                // Addition
                newResults.add(res + num);
                // Multiplication
                newResults.add(res * num);
                // Concatenation (||)
                long concatenated = Long.parseLong(res + "" + num); // Merge numbers as strings
                newResults.add(concatenated);
            }
            results = newResults;
        }
        
        // If the target can be achieved, return it; otherwise return 0
        return results.contains(target) ? target : 0;
    }

    // Function to read input data from a file
    public static long readAndProcessFile(String fileName) {
        long totalCalibrationResult = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalCalibrationResult += calculate(line); // Sum all solvable target values
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return totalCalibrationResult;
    }

    public static void main(String[] args) {
        // Input file containing the equations
        String fileName = "brigerepair2.txt";
        
        long totalCalibrationResult = readAndProcessFile(fileName);
        
        System.out.println("Total calibration result: " + totalCalibrationResult);
    }
}