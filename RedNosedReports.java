import java.util.*;

public class RedNosedReports {
    public static void main(String[] args){
        
         // Primer vhodnih podatkov
         String[] izvestaji = {
            "7 6 4 2 1",
            "1 2 7 8 9",
            "9 7 6 2 1",
            "1 3 2 4 5",
            "8 6 4 4 1",
            "1 3 6 7 9"
        };

        int steviloVarnih = 0;

        for (int i = 0; i < izvestaji.length; i++){
            if(jeVaren(izvestaji[i])){
                steviloVarnih++;
            }
        }

        System.out.println("Stevilo varnih izvestajev je: " + steviloVarnih);
        
    }

    public static boolean jeVaren(String izvestaj){
        String[] nivojiNizi = izvestaj.split(" ");
        int[] nivoji = new int[nivojiNizi.length];


        for (int i = 0; i < nivojiNizi.length; i++){
            nivoji[i] = Integer.parseInt(nivojiNizi[i]);
        }

        boolean narasca = true;
        boolean pada =  true;

        for(int i = 1; i < nivoji.length; i++){
            int razlika = nivoji[i] - nivoji[i - 1];


            //preverimo ce je zunaj obsega
            if(Math.abs(razlika) < 1 || Math.abs(razlika) > 3){
                return false;
            }

            //preverimo ali nivoji ne ustrezajo enemu pravilu (naraščajo ali padajo)
            if (razlika > 0){
                pada = false;
            } else if (razlika < 0){
                narasca = false;
            }

        }

        //varen, če nivoji vsi naraščajo ali vsi padajo
        return pada || narasca;

    }
}