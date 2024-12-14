import java.util.*;
import java.io.*;
import java.util.regex.*;

public class ClawContraption {

    static class Machine {
        int ax, ay, bx, by, prizeX, prizeY;

        Machine(int ax, int ay, int bx, int by, int prizeX, int prizeY) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.prizeX = prizeX;
            this.prizeY = prizeY;
        }
    }

    public static void main(String[] args) {
        // Preberi vhodne podatke iz datoteke
        List<Machine> machines = readMachinesFromFile("clawcontraption.txt");

        // Preveri vse naprave in jih izpiši
        for (Machine machine : machines) {
            System.out.println("Button A: X+" + machine.ax + ", Y+" + machine.ay);
            System.out.println("Button B: X+" + machine.bx + ", Y+" + machine.by);
            System.out.println("Prize: X=" + machine.prizeX + ", Y=" + machine.prizeY);
            System.out.println();
        }

        int maxTokens = 1500; // Maximum number of button presses allowed
        int totalTokens = 0;
        int prizesWon = 0;

        for (Machine machine : machines) {
            int result = findMinimumTokens(machine, maxTokens);
            if (result != -1) {
                prizesWon++;
                totalTokens += result;
            }
        }

        System.out.println("Prizes won: " + prizesWon);
        System.out.println("Total tokens used: " + totalTokens);
    }

    // Preberi naprave iz datoteke v želenem formatu
    static List<Machine> readMachinesFromFile(String filename) {
        List<Machine> machines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Uporabimo regularne izraze za iskanje podatkov
                Pattern pattern = Pattern.compile("Button A: X\\+(-?\\d+), Y\\+(-?\\d+)\\s+Button B: X\\+(-?\\d+), Y\\+(-?\\d+)\\s+Prize: X=(\\d+), Y=(\\d+)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    // Izvlečemo vrednosti za gumb A, gumb B in nagrado
                    int ax = Integer.parseInt(matcher.group(1));
                    int ay = Integer.parseInt(matcher.group(2));
                    int bx = Integer.parseInt(matcher.group(3));
                    int by = Integer.parseInt(matcher.group(4));
                    int prizeX = Integer.parseInt(matcher.group(5));
                    int prizeY = Integer.parseInt(matcher.group(6));

                    // Dodamo novo napravo
                    machines.add(new Machine(ax, ay, bx, by, prizeX, prizeY));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return machines;
    }

    // Poišči minimalno število tokenov, potrebnih za pridobitev nagrade
    static int findMinimumTokens(Machine machine, int maxPresses) {
        int minTokens = Integer.MAX_VALUE;

        // Brute-force vse kombinacije pritiskov gumba A in B
        for (int aPresses = 0; aPresses <= maxPresses; aPresses++) {
            for (int bPresses = 0; bPresses <= maxPresses; bPresses++) {
                int x = aPresses * machine.ax + bPresses * machine.bx;
                int y = aPresses * machine.ay + bPresses * machine.by;

                if (x == machine.prizeX && y == machine.prizeY) {
                    int tokens = aPresses * 3 + bPresses * 1; // A stane 3 tokene, B stane 1
                    minTokens = Math.min(minTokens, tokens);
                }
            }
        }

        return minTokens == Integer.MAX_VALUE ? -1 : minTokens;
    }
}
