import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryScanner2 {
    public static void main(String[] args) {
        // Putanja do datoteke
        String filePath = "input2.txt"; 

        try {
            // Učitavanje niza iz datoteke
            String corruptedString = readFile(filePath);

            // Obrada niza sa novim instrukcijama
            int sum = processInstructions(corruptedString);

            // Ispis rezultata
            System.out.println("Rezultat: " + sum);

        } catch (IOException e) {
            System.out.println("Došlo je do greške pri čitanju datoteke: " + e.getMessage());
        }
    }

    // Funkcija za čitanje datoteke
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

    // Funkcija za obradu instrukcija
    private static int processInstructions(String input) {
        Pattern mulPattern = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");

        Matcher matcher;
        boolean isEnabled = true; // Početno stanje omogućava `mul` instrukcije
        int totalSum = 0;

        // Iteracija kroz input i traženje instrukcija
        for (int i = 0; i < input.length(); ) {
            String subInput = input.substring(i);

            // Proveri `do()` instrukciju
            matcher = doPattern.matcher(subInput);
            if (matcher.find() && matcher.start() == 0) {
                isEnabled = true;
                i += matcher.end();
                continue;
            }

            // Proveri `don't()` instrukciju
            matcher = dontPattern.matcher(subInput);
            if (matcher.find() && matcher.start() == 0) {
                isEnabled = false;
                i += matcher.end();
                continue;
            }

            // Proveri `mul(x, y)` instrukciju
            matcher = mulPattern.matcher(subInput);
            if (matcher.find() && matcher.start() == 0) {
                if (isEnabled) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    totalSum += x * y;
                }
                i += matcher.end();
                continue;
            }

            // Ako ništa nije prepoznato, pređi na sledeći karakter
            i++;
        }

        return totalSum;
    }
}
