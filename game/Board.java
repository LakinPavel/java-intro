package game;

import game.Cell;
import game.Move;
import game.Position;
import game.Result;

public interface Board {
    Position getPosition();

    Cell[][] getPositionNew();

    Result makeMove(Move move);

    Cell getCellNew();

    Cell getCell(int a, int b);

}
