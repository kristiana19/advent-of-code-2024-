import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class MullItOverTwo {
    public static void main(String[] args) {
        // Pot do datoteke
        String potDatoteke = "input2.txt"; 

        try {
            // Branje niza iz datoteke
            String pokvarjeniNiz = preberiDatoteko(potDatoteke);

            // Obdelava niza z novimi navodili
            int vsota = obdelajNavodila(pokvarjeniNiz);

            // Izpis rezultata
            System.out.println("Rezultat: " + vsota);

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

    // Funkcija za obdelavo navodil
    private static int obdelajNavodila(String vhod) {
        Pattern mulVzorec = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
        Pattern doVzorec = Pattern.compile("do\\(\\)");
        Pattern dontVzorec = Pattern.compile("don't\\(\\)");

        Matcher ujemanje;
        boolean omogočeno = true; // Začetno stanje omogoča `mul` navodila
        int skupnaVsota = 0;

        // Iteracija skozi vhod in iskanje navodil
        for (int i = 0; i < vhod.length(); ) {
            String podVhod = vhod.substring(i);

            // Preveri `do()` navodilo
            ujemanje = doVzorec.matcher(podVhod);
            if (ujemanje.find() && ujemanje.start() == 0) {
                omogočeno = true;
                i += ujemanje.end();
                continue;
            }

            // Preveri `don't()` navodilo
            ujemanje = dontVzorec.matcher(podVhod);
            if (ujemanje.find() && ujemanje.start() == 0) {
                omogočeno = false;
                i += ujemanje.end();
                continue;
            }

            // Preveri `mul(x, y)` navodilo
            ujemanje = mulVzorec.matcher(podVhod);
            if (ujemanje.find() && ujemanje.start() == 0) {
                if (omogočeno) {
                    int x = Integer.parseInt(ujemanje.group(1));
                    int y = Integer.parseInt(ujemanje.group(2));
                    skupnaVsota += x * y;
                }
                i += ujemanje.end();
                continue;
            }

            // Če ni nič prepoznano, pojdi na naslednji znak
            i++;
        }

        return skupnaVsota;
    }
}
