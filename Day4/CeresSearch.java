import java.io.*;
import java.util.*;

public class CeresSearch {

    public static void main(String[] args) {
        // Pot do datoteke
        String potDoDatoteke = "ceressearch.txt";

        // Preberemo vse vrstice iz datoteke
        String[] mreza = preberiDatoteko(potDoDatoteke);

        // Beseda, ki jo iščemo
        String beseda = "XMAS";

        // Iskanje besede v mreži
        int steviloPojavitev = najdiBesedo(mreza, beseda);

        // Izpis števila pojavitve besede
        System.out.println("Beseda '" + beseda + "' se pojavi " + steviloPojavitev + " krat.");
    }

    // Funkcija za branje vsebine datoteke
    public static String[] preberiDatoteko(String potDoDatoteke) {
        List<String> vrstice = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(potDoDatoteke))) {
            String vrstica;
            while ((vrstica = br.readLine()) != null) {
                vrstice.add(vrstica);
            }
        } catch (IOException e) {
            System.out.println("Napaka pri branju datoteke: " + e.getMessage());
        }
        // Vrnemo seznam vrstic kot polje nizov
        return vrstice.toArray(new String[0]);
    }

    // Funkcija za iskanje besede v mreži
    public static int najdiBesedo(String[] mreza, String beseda) {
        int steviloPojavitev = 0;
        int steviloVrstic = mreza.length;
        int steviloStolpcev = mreza[0].length();

        // Poglej vse možne smeri (horizontalno, vertikalno, diagonalno)
        for (int vrstica = 0; vrstica < steviloVrstic; vrstica++) {
            for (int stolpec = 0; stolpec < steviloStolpcev; stolpec++) {
                // Poišči besedo v vseh smereh
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, 1, 0, beseda);  // Vodoravno desno
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, 0, 1, beseda);  // Navpično dol
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, 1, 1, beseda);  // Diagonalno desno dol
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, 1, -1, beseda); // Diagonalno desno gor
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, -1, 0, beseda); // Vodoravno levo
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, 0, -1, beseda); // Navpično gor
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, -1, -1, beseda); // Diagonalno levo dol
                steviloPojavitev += preveriSmer(mreza, vrstica, stolpec, -1, 1, beseda); // Diagonalno levo gor
            }
        }
        return steviloPojavitev;
    }

    // Preveri, ali beseda obstaja v določeni smeri
    public static int preveriSmer(String[] mreza, int vrstica, int stolpec, int dVrstica, int dStolpec, String beseda) {
        int dolzinaBesede = beseda.length();
        int steviloVrstic = mreza.length;
        int steviloStolpcev = mreza[0].length();

        // Preveri, ali beseda fit v mrežo v tej smeri
        for (int i = 0; i < dolzinaBesede; i++) {
            int novaVrstica = vrstica + i * dVrstica;
            int novStolpec = stolpec + i * dStolpec;

            // Preveri, ali je znotraj meja mreže
            if (novaVrstica < 0 || novaVrstica >= steviloVrstic || novStolpec < 0 || novStolpec >= steviloStolpcev) {
                return 0; // Beseda ne obstaja v tej smeri
            }

            // Preveri, ali se črka ujema
            if (mreza[novaVrstica].charAt(novStolpec) != beseda.charAt(i)) {
                return 0; // Beseda se ne ujema
            }
        }

        // Če je vse v redu, smo našli besedo
        return 1;
    }
}
