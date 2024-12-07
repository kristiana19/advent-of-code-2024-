import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class MullItOver {
    public static void main(String[] args) {
        // Pot do datoteke
        String potDatoteke = "input.txt"; 

        try {
            // Branje niza iz datoteke
            String pokvarjeniNiz = preberiDatoteko(potDatoteke);

            // Ekstrakcija vseh izrazov "mul(x, y)"
            List<Integer> rezultati = izvleciInIzracunaj(pokvarjeniNiz);

            // Izračunavanje vsot za del 1 in del 2
            int vsotaDel1 = rezultati.stream().reduce(0, Integer::sum); // Skupna vsota
            int vsotaDel2 = rezultati.size(); // Število "mul" izrazov

            // Izpis rezultatov
            System.out.println("Rezultat za del 1: " + vsotaDel1);
            System.out.println("Rezultat za del 2: " + vsotaDel2);

        } catch (IOException e) {
            System.out.println("Pri branju datoteke je prišlo do napake: " + e.getMessage());
        }
    }

    // Funkcija za branje datoteke
    private static String preberiDatoteko(String potDatoteke) throws IOException {
        StringBuilder vsebina = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(potDatoteke))) {
            String vrstica;
            while ((vrstica = br.readLine()) != null) {
                vsebina.append(vrstica);
            }
        }
        return vsebina.toString();
    }

    // Funkcija za ekstrakcijo in izračunavanje izrazov "mul(x, y)"
    private static List<Integer> izvleciInIzracunaj(String vhod) {
        List<Integer> rezultati = new ArrayList<>();
        Pattern vzorec = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
        Matcher ujemanje = vzorec.matcher(vhod);

        while (ujemanje.find()) {
            int x = Integer.parseInt(ujemanje.group(1)); // Prvo število
            int y = Integer.parseInt(ujemanje.group(2)); // Drugo število
            rezultati.add(x * y); // Izračun in dodajanje rezultata v seznam
        }

        return rezultati;
    }
}
