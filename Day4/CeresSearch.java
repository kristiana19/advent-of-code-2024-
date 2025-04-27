import java.io.*;
import java.util.*;

public class CeresSearch {

    public static void main(String[] args) {
        // Path to the file
        String filePath = "ceressearch.txt";

        // Read all lines from the file
        String[] grid = readFile(filePath);

        // Word to search for
        String word = "XMAS";

        // Search for the word in the grid
        int occurrences = findWord(grid, word);

        // Output the number of occurrences of the word
        System.out.println("The word '" + word + "' appears " + occurrences + " times.");
    }

    // Function to read the contents of a file
    public static String[] readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
        }
        // Return the list of lines as an array of strings
        return lines.toArray(new String[0]);
    }

    // Function to search for the word in the grid
    public static int findWord(String[] grid, String word) {
        int occurrences = 0;
        int numRows = grid.length;
        int numCols = grid[0].length();

        // Check all possible directions (horizontal, vertical, diagonal)
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Search for the word in all directions
                occurrences += checkDirection(grid, row, col, 1, 0, word);  // Horizontally right
                occurrences += checkDirection(grid, row, col, 0, 1, word);  // Vertically down
                occurrences += checkDirection(grid, row, col, 1, 1, word);  // Diagonally right down
                occurrences += checkDirection(grid, row, col, 1, -1, word); // Diagonally right up
                occurrences += checkDirection(grid, row, col, -1, 0, word); // Horizontally left
                occurrences += checkDirection(grid, row, col, 0, -1, word); // Vertically up
                occurrences += checkDirection(grid, row, col, -1, -1, word); // Diagonally left up
                occurrences += checkDirection(grid, row, col, -1, 1, word); // Diagonally left down
            }
        }
        return occurrences;
    }

    // Check if the word exists in a given direction
    public static int checkDirection(String[] grid, int row, int col, int dRow, int dCol, String word) {
        int wordLength = word.length();
        int numRows = grid.length;
        int numCols = grid[0].length();

        // Check if the word fits in the grid in this direction
        for (int i = 0; i < wordLength; i++) {
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;

            // Check if inside the bounds of the grid
            if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                return 0; // The word does not exist in this direction
            }

            // Check if the character matches
            if (grid[newRow].charAt(newCol) != word.charAt(i)) {
                return 0; // The word does not match
            }
        }

        // If everything matches, we found the word
        return 1;
    }
}