import java.util.*; 

public class HistorianHysteria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
                
        // Kreiranje dinamičkih lista za unos elemenata
        List<Integer> leviSeznam = new ArrayList<>(); // Lista za levi popis
        List<Integer> desniSeznam = new ArrayList<>(); // Lista za desni popis

        System.out.println("Unesite elemente u formatima: 'levi desni'. Za kraj unosa pritisnite Enter na praznom retku:");
        
        // Čitanje unosa red po red
        while (sc.hasNextLine()) { // Provera da li postoji još redova za unos
            String linija = sc.nextLine().trim(); // Čitanje trenutnog reda i uklanjanje praznih znakova sa krajeva
            if (linija.isEmpty()) { // Ako je red prazan, prekida se unos
                break; // Izlazak iz petlje
            }

            String[] brojevi = linija.split("\\s+"); // Razdvajanje reda na delove prema razmacima
            if (brojevi.length == 2) { // Provera da li postoje tačno dva broja u redu
                leviSeznam.add(Integer.parseInt(brojevi[0])); // Dodavanje prvog broja u levi popis
                desniSeznam.add(Integer.parseInt(brojevi[1])); // Dodavanje drugog broja u desni popis
            } else { // Ako red ne sadrži tačno dva broja, prikazuje se poruka o grešci
                System.out.println("Nepravilan unos! Unesite dva broja odvojena razmakom.");
            }
        }

        // Pretvori dinamičke liste u nizove
        int[] levo = leviSeznam.stream().mapToInt(Integer::intValue).toArray(); // Konvertovanje liste u niz za levi popis
        int[] desno = desniSeznam.stream().mapToInt(Integer::intValue).toArray(); // Konvertovanje liste u niz za desni popis

        // Izračun udaljenosti između popisa
        int razdalja = izracunajRazdaljo(levo, desno); // Poziv funkcije za izračunavanje udaljenosti

        // Ispis rezultata
        System.out.println("Ukupna udaljenost između popisa je: " + razdalja); // Prikaz ukupne udaljenosti
    }

    // Funkcija za izračunavanje udaljenosti između dva popisa
    public static int izracunajRazdaljo(int[] levo, int[] desno) {
        Arrays.sort(levo); // Sortiranje levog popisa u rastućem redosledu
        Arrays.sort(desno); // Sortiranje desnog popisa u rastućem redosledu

        int razdalja = 0; // Inicijalizacija varijable za čuvanje ukupne udaljenosti
        for (int i = 0; i < levo.length; i++) { // Iteracija kroz elemente oba popisa
            razdalja += Math.abs(levo[i] - desno[i]); // Dodavanje apsolutne razlike odgovarajućih elemenata
        }

        return razdalja; // Vraćanje ukupne udaljenosti
    }
}
