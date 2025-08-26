package game;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import game.Cell;
import game.MnkBoard;

public class RectangleBoard extends MnkBoard {
    public RectangleBoard(int m, int n, int k) {
        this.k = k;
        this.n = n;
        this.m = m;
        this.cells = new Cell[this.m][this.n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        this.siz = n * m;
        turn = Cell.X;
    }
}
