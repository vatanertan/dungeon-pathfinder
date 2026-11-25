# Dungeon Pathfinder

An object-oriented dungeon pathfinding engine written in Java. Given a 2D grid of rooms with varying effects, the application uses a recursive backtracking algorithm to find the optimal path from the starting point to the exit while collecting the required key and maximizing the player's remaining health.

## Features

- **Recursive Backtracking Solver:** Explores all valid routes in a deterministic order (Up, Right, Down, Left) to find the path that yields the highest remaining health, breaking ties by the fewest steps taken.
- **State Snapshot & Restoration:** Captures and restores player health, shield value, and key status (`PlayerState`) at each recursion step without side effects.
- **Extensible Room Hierarchy:** Implements multi-level inheritance (`Rooms` -> `DamageRoom` / `ArtifactRoom`) and the Factory design pattern (`RoomFactory`) to instantiate room behaviors dynamically from grid tokens:
  - `E` / `X`: Empty / Exit room (passive)
  - `K`: Key room (grants the exit key)
  - `H`: Heal room (+15 HP, capped at max health)
  - `T`: Trap room (-20 HP)
  - `M`: Monster room (-30 HP)
  - `L`: Relic Heal room (+10 HP)
  - `A`: Armor Relic room (+6 Shield)
  - `B`: Boost Relic room (+4 Shield)

## Project Structure

```text
dungeon-pathfinder/
├── src/
│   ├── Main.java
│   ├── Dungeon.java
│   ├── Player.java
│   ├── PlayerState.java
│   ├── RoomFactory.java
│   └── Rooms.java
├── tests/
│   ├── sample_inputs/
│   │   └── case1.txt ... case7.txt
│   └── expected_outputs/
│       └── case1_output.txt ... case7_output.txt
├── .gitignore
└── README.md
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher

## Compilation & Usage

1. Compile the source files into an output directory:
   ```bash
   javac -d out src/*.java
   ```

2. Run the solver with an input and output file path:
   ```bash
   java -cp out Main tests/sample_inputs/case1.txt actual_output.txt
   ```

3. Verify the output against the expected result:
   ```bash
   diff -u tests/expected_outputs/case1_output.txt actual_output.txt
   ```