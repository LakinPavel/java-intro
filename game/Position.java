package game;

import game.Cell;
import game.Move;

public interface Position {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
}
