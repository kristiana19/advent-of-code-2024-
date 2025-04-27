import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class BridgeRepair {

    // Function to calculate all possible results using operators between numbers
    public static long calculate(String equation) {
        String[] parts = equation.split(": ");
        long target = Long.parseLong(parts[0]);
        String[] numbers = parts[1].split(" ");
        
        // Check all possible combinations of operators (+ and *)
        List<Long> results = new ArrayList<>();
        results.add(Long.parseLong(numbers[0]));
        
        for (int i = 1; i < numbers.length; i++) {
            List<Long> newResults = new ArrayList<>();
            long num = Long.parseLong(numbers[i]);
            
            // Add all possibilities using + and * for each number
            for (long res : results) {
                newResults.add(res + num);
                newResults.add(res * num);
            }
            results = newResults;
        }
        
        return results.contains(target) ? target : 0;  // If the target value is among the results, return it
    }

    // Function to read input data from a file
    public static long readAndProcessFile(String fileName) {
        long totalCalibrationResult = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalCalibrationResult += calculate(line);  // Sum up all solvable target values
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return totalCalibrationResult;
    }

    public static void main(String[] args) {
        // Input file containing the equations
        String fileName = "brigerepair.txt";
        
        long totalCalibrationResult = readAndProcessFile(fileName);
        
        System.out.println("Total calibration result: " + totalCalibrationResult);
    }
}