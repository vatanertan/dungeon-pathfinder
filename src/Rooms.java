/**
 * Level 1: Base class for all room types in the dungeon.
 */
public class Rooms {
    
    /**
     * Empty body for the base method. Subclasses will override this.
     * * @param player The player entering the room.
     */
    public void apply(Player player) {
    }
}

/**
 * Level 2: Subclass for rooms that deal damage to the player.
 */
class DamageRoom extends Rooms {
    protected int damageAmount;

    public DamageRoom(int damage) {
        this.damageAmount = damage;
    }

    @Override
    public void apply(Player player) {
        player.damage(damageAmount);
    }
}

/**
 * Level 3: A trap room that deals 20 damage.
 */
class TrapRoom extends DamageRoom {
    public TrapRoom() {
        super(20);
    }
}

/**
 * Level 3: A monster room that deals 30 damage.
 */
class MonsterRoom extends DamageRoom {
    public MonsterRoom() {
        super(30);
    }
}

/**
 * Level 2: Subclass for rooms that contain special artifacts.
 */
class ArtifactRoom extends Rooms {
    // No additional logic needed, inherits everything from the base class
}

/**
 * Level 3: Artifact room that heals 10 HP.
 */
class RelicHealRoom extends ArtifactRoom {
    @Override
    public void apply(Player player) {
        player.heal(10);
    }
}

/**
 * Level 3: Artifact room that gives a 6 HP shield.
 */
class ArmorRelicRoom extends ArtifactRoom {
    @Override
    public void apply(Player player) {
        player.setShield(6);
    }
}

/**
 * Level 3: Artifact room that gives a 4 HP shield.
 */
class BoostRelicRoom extends ArtifactRoom {
    @Override
    public void apply(Player player) {
        player.setShield(4);
    }
}

/**
 * Level 2: Standard room that heals 15 HP.
 */
class HealRoom extends Rooms {
    @Override
    public void apply(Player player) {
        player.heal(15);
    }
}

/**
 * Level 2: Room containing the required key.
 */
class KeyRoom extends Rooms {
    @Override
    public void apply(Player player) {
        player.obtainKey();
    }
}

/**
 * Level 2: Room with no effect (used for Empty or Exit rooms).
 */
class PassiveRoom extends Rooms {
    @Override
    public void apply(Player player) {
        // Does nothing to the player
    }
}