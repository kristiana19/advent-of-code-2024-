import java.util.Arrays;
import java.util.Scanner;

public class HistorianHysteria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Vprašaj uporabnika za velikost seznama
        System.out.print("Vnesite število elementov v seznamu: ");
        int n = sc.nextInt();

        // Ustvari sezname
        int[] leviSeznam = new int[n];
        int[] desniSeznam = new int[n];

        // Preberi elemente za levi seznam
        System.out.println("Vnesite elemente za levi seznam:");
        for (int i = 0; i < n; i++) {
            leviSeznam[i] = sc.nextInt();
        }

        // Preberi elemente za desni seznam
        System.out.println("Vnesite elemente za desni seznam:");
        for (int i = 0; i < n; i++) {
            desniSeznam[i] = sc.nextInt();
        }

        // Izračunaj razdaljo med seznamoma
        int razdalja = izracunajRazdaljo(leviSeznam, desniSeznam);

        // Izpiši rezultat
        System.out.println("Skupna razdalja med seznamoma je: " + razdalja);
    }

    public static int izracunajRazdaljo(int[] levo, int[] desno) {
        // Sortiraj sezname
        Arrays.sort(levo);
        Arrays.sort(desno);

        int razdalja = 0;
        for (int i = 0; i < levo.length; i++) {
            razdalja += Math.abs(levo[i] - desno[i]);
        }

        return razdalja;
    }
}