import java.util.*;

public class HistorianHysteriaTwo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kreiranje seznamov
        List<Integer> leviSeznam = new ArrayList<>();
        List<Integer> desniSeznam = new ArrayList<>();

        // Vnos številk za levi in desni seznam
        System.out.println("Vnesite številke za levi in desni seznam v dveh stolpcih (za konec vnesite 0 0):");
        
        while (true) {
            // Vnos dveh številk (eno za levi seznam, eno za desni seznam)
            int levaStevilka = scanner.nextInt();
            int desnaStevilka = scanner.nextInt();

            // Prekine vnos, če sta obe številki 0
            if (levaStevilka == 0 && desnaStevilka == 0) {
                break;
            }

            // Dodajanje številk v ustrezne sezname
            leviSeznam.add(levaStevilka);
            desniSeznam.add(desnaStevilka);
        }

        // Izračunavanje podobnosti
        int rezultatPodobnosti = 0;
        for (Integer stevilka : leviSeznam) {
            // Šteje kolikokrat se številka iz levega seznama pojavi v desnem seznamu
            int steviloPojavitev = Collections.frequency(desniSeznam, stevilka);
            rezultatPodobnosti += stevilka * steviloPojavitev;
        }

        // Ipisovanje rezultata
        System.out.println("Skupna podobnost med seznamoma je: " + rezultatPodobnosti);

        scanner.close();
    }
}
