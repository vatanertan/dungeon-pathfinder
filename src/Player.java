/**
 * Represents the player navigating the dungeon.
 */
public class Player {
    private int health;
    private int maxHealth;
    private boolean hasKey;
    private int shield;

    /**
     * Initialize the player with starting health.
     * * @param startingHealth The initial health points of the player.
     */
    public Player(int startingHealth) {
        this.health = startingHealth;
        this.maxHealth = startingHealth;
        this.hasKey = false;
        this.shield = 0;
    }

    /**
     * Apply damage after checking if a shield is active.
     * * @param damageAmount The amount of damage to deal.
     */
    public void damage(int damageAmount) {
        int damageTaken = Math.max(0, damageAmount - shield);
        shield = 0; // Shield breaks after taking a hit
        health -= damageTaken;
    }

    /**
     * Increase health without exceeding the maximum health limit.
     * * @param healAmount The amount of health to restore.
     */
    public void heal(int healAmount) {
        this.health = Math.min(this.maxHealth, this.health + healAmount);
    }

    /**
     * Set a new active shield value.
     * * @param shieldValue The power of the shield.
     */
    public void setShield(int shieldValue) {
        this.shield = shieldValue;
    }

    /**
     * Mark that the player successfully found the key.
     */
    public void obtainKey() {
        this.hasKey = true;
    }

    /**
     * Check if the player currently holds the key.
     * * @return true if the player has the key, false otherwise.
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * Return the player's current health points.
     * * @return The current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Save the current state before entering a new room to allow backtracking.
     * * @return A PlayerState object containing the current health, key status, and shield.
     */
    public PlayerState saveState() {
        return new PlayerState(health, hasKey, shield);
    }

    /**
     * Restore the state when backtracking out of a room.
     * * @param previousState The saved state to revert back to.
     */
    public void restoreState(PlayerState previousState) {
        this.health = previousState.health;
        this.hasKey = previousState.hasKey;
        this.shield = previousState.shield;
    }
}