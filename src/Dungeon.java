import java.util.ArrayList;
import java.util.List;

/**
 * Manages the dungeon grid, pathfinding algorithm, and formats the output.
 */
public class Dungeon {

    private Rooms[][] dungeonGrid;
    private int rowCount, colCount;
    private int startRow, startCol, exitRow, exitCol;
    private boolean isKeyInDungeon = false;

    private int maximumHealth;
    private int bestHealth = -1;
    private List<String> bestExitPath = new ArrayList<>();

    /**
     * Initializes the dungeon setup and checking if a key exists.
     * * @param grid The 2D array representing the dungeon.
     * @param startRow The starting row coordinate.
     * @param startCol The starting column coordinate.
     * @param exitRow The exit row coordinate.
     * @param exitCol The exit column coordinate.
     * @param startHealth The player's starting health.
     */
    public Dungeon(Rooms[][] grid, int startRow, int startCol, int exitRow, int exitCol, int startHealth) {
        this.dungeonGrid = grid;
        this.rowCount = grid.length;
        this.colCount = grid[0].length;
        this.startRow = startRow;
        this.startCol = startCol;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        this.maximumHealth = startHealth;
        
        // Check the entire grid to see if there is at least one key room
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (dungeonGrid[i][j] instanceof KeyRoom) {
                    isKeyInDungeon = true;
                }
            }
        }
    }

    /**
     * Starts the recursive backtracking algorithm.
     */
    public void solve() {
        boolean[][] visitedRooms = new boolean[rowCount][colCount];
        Player player = new Player(maximumHealth);
        backtrack(startRow, startCol, player, visitedRooms, new ArrayList<>());
    }

    /**
     * Recursive function to explore all possible paths to the exit.
     * * @param currentRow The current row position.
     * @param currentCol The current column position.
     * @param player The player object tracking health and items.
     * @param visitedRooms 2D boolean array of visited rooms.
     * @param currentPath The list tracking the current sequence of steps.
     */
    private void backtrack(int currentRow, int currentCol, Player player, boolean[][] visitedRooms, List<String> currentPath) {
        // Stop exploring this path if the player is dead
        if (player.getHealth() <= 0) {
            return;
        }

        visitedRooms[currentRow][currentCol] = true;

        Rooms currentRoom = dungeonGrid[currentRow][currentCol];
        PlayerState previousState = player.saveState();

        // Apply room effects (damage, heal, key, etc.)
        currentRoom.apply(player);

        // Record this step in the current path
        currentPath.add("(" + currentRow + "," + currentCol + "," + player.getHealth() + ")");

        // Check if we successfully reached the exit with the key
        if (currentRow == exitRow && currentCol == exitCol && player.hasKey()) {
            // Update best path if we have more health, or same health but shorter path
            if (player.getHealth() > bestHealth ||
                (player.getHealth() == bestHealth && currentPath.size() < bestExitPath.size())) {
                bestHealth = player.getHealth();
                bestExitPath = new ArrayList<>(currentPath);
            }
        }

        // Explore adjacent rooms in the strict order: Up, Right, Down, Left

        // 1. Move Up
        if (currentRow - 1 >= 0 && !visitedRooms[currentRow - 1][currentCol]) {
            backtrack(currentRow - 1, currentCol, player, visitedRooms, currentPath);
        }
        
        // 2. Move Right
        if (currentCol + 1 < colCount && !visitedRooms[currentRow][currentCol + 1]) {
            backtrack(currentRow, currentCol + 1, player, visitedRooms, currentPath);
        }

        // 3. Move Down
        if (currentRow + 1 < rowCount && !visitedRooms[currentRow + 1][currentCol]) {
            backtrack(currentRow + 1, currentCol, player, visitedRooms, currentPath);
        }

        // 4. Move Left
        if (currentCol - 1 >= 0 && !visitedRooms[currentRow][currentCol - 1]) {
            backtrack(currentRow, currentCol - 1, player, visitedRooms, currentPath);
        }

        // Undo the room's effect and step back to explore other routes
        player.restoreState(previousState);
        currentPath.remove(currentPath.size() - 1);
        visitedRooms[currentRow][currentCol] = false;
    }

    /**
     * Generates the final output text based on the results of the pathfinding algorithm.
     * * @return A formatted string containing the result, health, steps, and path.
     */
    public String getResult() {
        StringBuilder finalOutput = new StringBuilder();

        if (bestHealth == -1) {
            finalOutput.append("RESULT: FAILURE\n");
            finalOutput.append("MAX_HEALTH: ").append(maximumHealth).append("\n");
            if (!isKeyInDungeon) {
                finalOutput.append("REASON: No key found.\n");
            } else {
                finalOutput.append("REASON: Player died before reaching the exit.\n");
            }
            return finalOutput.toString();
        }

        finalOutput.append("RESULT: SUCCESS\n");
        finalOutput.append("MAX_HEALTH: ").append(maximumHealth).append("\n");
        finalOutput.append("REMAINING_HEALTH: ").append(bestHealth).append("\n");
        finalOutput.append("STEPS: ").append(bestExitPath.size() - 1).append("\n");
        finalOutput.append("PATH:\n");

        for (int i = 0; i < bestExitPath.size(); i++) {
            finalOutput.append(bestExitPath.get(i));
            if (i != bestExitPath.size() - 1) {
                finalOutput.append(" -> ");
            }
        }
        finalOutput.append("\n");

        return finalOutput.toString();
    }
}