package game;

import game.Cell;
import game.Move;

public interface Player {
    Move move(Cell[][] position, Cell cell);
}
