/**
 * Creates different room objects based on the input letter from the input file.
 */
public class RoomFactory {
    
    /**
     * Returns the correct room subclass for the given letter.
     * * @param roomLetter A string representing the type of room (H, T, M, etc.).
     * @return A specific instance of a Rooms subclass.
     */
    public static Rooms createRoom(String roomLetter) {
        switch (roomLetter) {
            case "H": return new HealRoom();
            case "T": return new TrapRoom();
            case "M": return new MonsterRoom();
            case "E": return new PassiveRoom();
            case "X": return new PassiveRoom();
            case "K": return new KeyRoom();
            case "L": return new RelicHealRoom();
            case "A": return new ArmorRelicRoom();
            case "B": return new BoostRelicRoom();
            default: return new PassiveRoom();
        }
    }
}