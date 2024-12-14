import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class BridgeRepair {

    // Funkcija za izračun vseh možnih rezultatov s pomočjo operatorjev med številkami
    public static long calculate(String equation) {
        String[] parts = equation.split(": ");
        long target = Long.parseLong(parts[0]);
        String[] numbers = parts[1].split(" ");
        
        // Preverimo vse možne kombinacije operatorjev (+ in *)
        List<Long> results = new ArrayList<>();
        results.add(Long.parseLong(numbers[0]));
        
        for (int i = 1; i < numbers.length; i++) {
            List<Long> newResults = new ArrayList<>();
            long num = Long.parseLong(numbers[i]);
            
            // Dodamo vse možnosti s + in * za vsako število
            for (long res : results) {
                newResults.add(res + num);
                newResults.add(res * num);
            }
            results = newResults;
        }
        
        return results.contains(target) ? target : 0;  // Če je testna vrednost ena izmed rezultatov, jo vrnemo
    }

    // Funkcija za branje vhodnih podatkov iz datoteke
    public static long readAndProcessFile(String fileName) {
        long totalCalibrationResult = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalCalibrationResult += calculate(line);  // Seštejemo vse resljive testne vrednosti
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return totalCalibrationResult;
    }

    public static void main(String[] args) {
        // Vhodna datoteka z enačbami
        String fileName = "brigerepair.txt";
        
        long totalCalibrationResult = readAndProcessFile(fileName);
        
        System.out.println("Skupni rezultat kalibracije: " + totalCalibrationResult);
    }
}
