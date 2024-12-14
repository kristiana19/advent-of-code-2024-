import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BridgeRepair2 {

    // Funkcija za izračun vseh možnih rezultatov s tremi operatorji (+, *, ||)
    public static long calculate(String equation) {
        String[] parts = equation.split(": ");
        long target = Long.parseLong(parts[0]);
        String[] numbers = parts[1].split(" ");
        
        // Preverimo vse možne kombinacije operatorjev (+, *, ||)
        List<Long> results = new ArrayList<>();
        results.add(Long.parseLong(numbers[0]));
        
        for (int i = 1; i < numbers.length; i++) {
            List<Long> newResults = new ArrayList<>();
            long num = Long.parseLong(numbers[i]);
            
            for (long res : results) {
                // Seštevanje
                newResults.add(res + num);
                // Množenje
                newResults.add(res * num);
                // Konkatenacija (||)
                long concatenated = Long.parseLong(res + "" + num); // Združimo števili kot niza
                newResults.add(concatenated);
            }
            results = newResults;
        }
        
        // Če lahko dosežemo cilj (target), ga vrnemo; sicer vrnemo 0
        return results.contains(target) ? target : 0;
    }

    // Funkcija za branje vhodnih podatkov iz datoteke
    public static long readAndProcessFile(String fileName) {
        long totalCalibrationResult = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalCalibrationResult += calculate(line); // Seštejemo vse resljive testne vrednosti
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return totalCalibrationResult;
    }

    public static void main(String[] args) {
        // Vhodna datoteka z enačbami
        String fileName = "brigerepair2.txt";
        
        long totalCalibrationResult = readAndProcessFile(fileName);
        
        System.out.println("Skupni rezultat kalibracije: " + totalCalibrationResult);
    }
}
