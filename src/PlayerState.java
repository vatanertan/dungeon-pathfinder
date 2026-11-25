/**
 * Holds the player's saved state during dungeon exploration.
 */
public class PlayerState {
    int health;
    boolean hasKey;
    int shield;

    /**
     * Constructor to save the current values.
     * * @param currentHealth The player's health at the time of saving.
     * @param currentKeyStatus The player's key possession status at the time of saving.
     * @param currentShield The player's shield value at the time of saving.
     */
    public PlayerState(int currentHealth, boolean currentKeyStatus, int currentShield) {
        this.health = currentHealth;
        this.hasKey = currentKeyStatus;
        this.shield = currentShield;
    }
}