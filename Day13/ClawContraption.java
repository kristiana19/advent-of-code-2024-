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
        // Read input data from file
        List<Machine> machines = readMachinesFromFile("clawcontraption.txt");

        // Display all machines
        for (Machine machine : machines) {
            System.out.println("Button A: X+" + machine.ax + ", Y+" + machine.ay);
            System.out.println("Button B: X+" + machine.bx + ", Y+" + machine.by);
            System.out.println("Prize: X=" + machine.prizeX + ", Y=" + machine.prizeY);
            System.out.println();
        }

        int maxTokens = 1500; // Maximum number of button presses allowed
        int totalTokens = 0;
        int prizesWon = 0;

        // Try to win prizes with each machine
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

    // Read machines from file in the expected format
    static List<Machine> readMachinesFromFile(String filename) {
        List<Machine> machines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Use regular expressions to extract data
                Pattern pattern = Pattern.compile("Button A: X\\+(-?\\d+), Y\\+(-?\\d+)\\s+Button B: X\\+(-?\\d+), Y\\+(-?\\d+)\\s+Prize: X=(\\d+), Y=(\\d+)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    // Extract values for button A, button B, and prize
                    int ax = Integer.parseInt(matcher.group(1));
                    int ay = Integer.parseInt(matcher.group(2));
                    int bx = Integer.parseInt(matcher.group(3));
                    int by = Integer.parseInt(matcher.group(4));
                    int prizeX = Integer.parseInt(matcher.group(5));
                    int prizeY = Integer.parseInt(matcher.group(6));

                    // Add a new machine
                    machines.add(new Machine(ax, ay, bx, by, prizeX, prizeY));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return machines;
    }

    // Find the minimum number of tokens needed to get the prize
    static int findMinimumTokens(Machine machine, int maxPresses) {
        int minTokens = Integer.MAX_VALUE;

        // Brute-force all combinations of button A and B presses
        for (int aPresses = 0; aPresses <= maxPresses; aPresses++) {
            for (int bPresses = 0; bPresses <= maxPresses; bPresses++) {
                int x = aPresses * machine.ax + bPresses * machine.bx;
                int y = aPresses * machine.ay + bPresses * machine.by;

                if (x == machine.prizeX && y == machine.prizeY) {
                    int tokens = aPresses * 3 + bPresses * 1; // A costs 3 tokens, B costs 1 token
                    minTokens = Math.min(minTokens, tokens);
                }
            }
        }

        return minTokens == Integer.MAX_VALUE ? -1 : minTokens;
    }
}