package game;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import game.Cell;
import game.MnkBoard;

public class CircleBoard extends MnkBoard {
    public CircleBoard(int d, int k) {
        this.k = k;
        this.n = d;
        this.m = d;
        this.cells = new Cell[this.m][this.n];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if ((2 * i - d + 1) * (2 * i - d + 1) + (2 * j - d + 1) * (2 * j - d + 1) <= d * d) {
                    cells[i][j] = Cell.E;
                    this.siz += 1;
                } else {
                    cells[i][j] = Cell.Out;
                }
            }
        }
        turn = Cell.X;
    }
}
