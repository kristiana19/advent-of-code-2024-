import java.util.*;

public class RedNosedReportsTwo {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Pojasnilo za uporabnika
        System.out.println("Vnesite številke v eni vrstici (ločene s presledki), za konec vnesite 'konec'.");

        int steviloVarnih = 0;

        while (true) {
            
            String izvestaj = scanner.nextLine();

            // Preverimo, če je vnos 'konec', če je, končamo z vnosom
            if (izvestaj.equalsIgnoreCase("konec")) {
                break;
            }

            // Preverimo, ali je izvestaj varen z upoštevanjem Problem Dampenerja
            if (jeVarenZProblemDampenerjem(izvestaj)) {
                steviloVarnih++;
            }
        }

        // Ispis števila varnih izvestajev
        System.out.println("Število varnih izvestajev je: " + steviloVarnih);
        
        scanner.close();
    }

    // Preverimo, ali je izvestaj varen 
    public static boolean jeVarenZProblemDampenerjem(String izvestaj) {
        // Razdelimo vhodni string na posamezne številke
        String[] nivojiNizi = izvestaj.split(" ");
        int[] nivoji = new int[nivojiNizi.length];

        // Pretvorimo niz v celoštevilski array
        for (int i = 0; i < nivojiNizi.length; i++) {
            nivoji[i] = Integer.parseInt(nivojiNizi[i]);
        }

        // Preverimo, ali je poročilo že varno brez odstranjevanja
        if (jeVaren(izvestaj)) {
            return true;
        }

        // Če poročilo ni varno, preverimo, ali ga lahko popravimo z odstranitvijo ene ravni
        for (int i = 0; i < nivoji.length; i++) {
            // Ustvarimo nov niz brez i-te ravni
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nivoji.length; j++) {
                if (j != i) {
                    sb.append(nivoji[j]).append(" ");
                }
            }

            // Preverimo, če je poročilo varno po odstranitvi ravni
            if (jeVaren(sb.toString().trim())) {
                return true;
            }
        }

        // Če nobena odstranitev ravni ne pomaga, poročilo ni varno
        return false;
    }

    // Preverimo, ali je izvestaj varen (brez odstranjevanja)
    public static boolean jeVaren(String izvestaj) {
        // Razdelimo vhodni string na posamezne številke
        String[] nivojiNizi = izvestaj.split(" ");
        int[] nivoji = new int[nivojiNizi.length];

        // Pretvorimo niz v celoštevilski array
        for (int i = 0; i < nivojiNizi.length; i++) {
            nivoji[i] = Integer.parseInt(nivojiNizi[i]);
        }

        boolean narasca = true;
        boolean pada = true;

        for (int i = 1; i < nivoji.length; i++) {
            int razlika = nivoji[i] - nivoji[i - 1];

            // Preverimo, če je razlika znotraj dovoljenega obsega (-3 do 3)
            if (Math.abs(razlika) < 1 || Math.abs(razlika) > 3) {
                return false;
            }

            // Preverimo, ali nivoji naraščajo ali padajo
            if (razlika > 0) {
                pada = false;
            } else if (razlika < 0) {
                narasca = false;
            }
        }

        // Varen, če nivoji vsi naraščajo ali vsi padajo
        return pada || narasca;
    }
}
