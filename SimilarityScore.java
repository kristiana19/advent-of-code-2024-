import java.util.*;

public class SimilarityScore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kreiranje lista
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // Unos brojeva za levi i desni list
        System.out.println("Unesite brojeve za levi i desni list u dve kolone (za kraj unesite 0 0):");
        
        while (true) {
            // Unos dva broja (jedan za levi list, jedan za desni list)
            int leftNumber = scanner.nextInt();
            int rightNumber = scanner.nextInt();

            // Prekida unos ako su oba broja 0
            if (leftNumber == 0 && rightNumber == 0) {
                break;
            }

            // Dodavanje brojeva u odgovarajuće liste
            leftList.add(leftNumber);
            rightList.add(rightNumber);
        }

        // Izračunavanje sličnosti
        int similarityScore = 0;
        for (Integer number : leftList) {
            // Brojanje koliko puta broj iz levog lista pojavljuje u desnom listu
            int count = Collections.frequency(rightList, number);
            similarityScore += number * count;
        }

        // Ispis rezultata
        System.out.println("Ukupna sličnost između listi je: " + similarityScore);

        scanner.close();
    }
}
