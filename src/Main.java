import java.io.*;

/**
 * The main class that handles file reading, writing, and starting the game.
 */
public class Main {
    
    /**
     * Application entry point with 'throws Exception' to handle potential IO issues.
     * * @param args Command line arguments (input and output file paths).
     * @throws Exception If an error occurs during file reading or writing.
     */
    public static void main(String[] args) throws Exception {
        
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

        // Read the dungeon dimensions (rows and columns)
        String[] sizeLine = reader.readLine().trim().split(" ");
        int rowCount = Integer.parseInt(sizeLine[0]);
        int colCount = Integer.parseInt(sizeLine[1]);

        // Read the player's initial health
        int initialHealth = Integer.parseInt(reader.readLine().trim());

        // Read the starting coordinates
        String[] startLine = reader.readLine().trim().split(" ");
        int startRow = Integer.parseInt(startLine[0]);
        int startCol = Integer.parseInt(startLine[1]);

        // Read the exit coordinates
        String[] exitLine = reader.readLine().trim().split(" ");
        int exitRow = Integer.parseInt(exitLine[0]);
        int exitCol = Integer.parseInt(exitLine[1]);

        // Create the dungeon grid
        Rooms[][] grid = new Rooms[rowCount][colCount];

        // Fill the grid with room objects created by the factory
        for (int i = 0; i < rowCount; i++) {
            String[] roomTokens = reader.readLine().trim().split(" ");
            for (int j = 0; j < colCount; j++) {
                grid[i][j] = RoomFactory.createRoom(roomTokens[j]);
            }
        }

        reader.close();

        // Create the dungeon object and start the pathfinding algorithm
        Dungeon dungeon = new Dungeon(grid, startRow, startCol, exitRow, exitCol, initialHealth);
        dungeon.solve();

        // Write the final results to the output file
        writer.write(dungeon.getResult());
        writer.close();
    }
}