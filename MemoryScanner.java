import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryScanner {
    public static void main(String[] args) {
        // Putanja do datoteke
        String filePath = "input.txt"; 

        try {
            // Učitavanje niza iz datoteke
            String corruptedString = readFile(filePath);

            // Ekstrakcija svih izraza "mul(x, y)"
            List<Integer> results = extractAndCalculate(corruptedString);

            // Računanje suma za deo 1 i deo 2
            int sumPart1 = results.stream().reduce(0, Integer::sum); // Ukupna suma
            int sumPart2 = results.size(); // Broj "mul" izraza

            // Ispis rezultata
            System.out.println("Rezultat za deo 1: " + sumPart1);
            System.out.println("Rezultat za deo 2: " + sumPart2);

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

    // Funkcija za ekstrakciju i računanje izraza "mul(x, y)"
    private static List<Integer> extractAndCalculate(String input) {
        List<Integer> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1)); // Prvi broj
            int y = Integer.parseInt(matcher.group(2)); // Drugi broj
            results.add(x * y); // Računanje i dodavanje rezultata u listu
        }

        return results;
    }
}
