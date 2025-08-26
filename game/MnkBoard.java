package game;

import java.io.PrintStream;
import java.util.Scanner;

import game.Board;
import game.Cell;
import game.Move;
import game.Position;
import game.Result;

public class MnkBoard implements Board, Position {
    final PrintStream out = new PrintStream(System.out);
    final Scanner in = new Scanner(System.in);
    protected Cell[][] cells;
    protected Cell turn;
    protected int siz = 0;
    protected int k;
    protected int n;
    protected int m;
    private int countMove = 0;
    private Integer[] Lines = new Integer[12];
    private Integer[] CountL = new Integer[4];

    private boolean Diag(int indexColumn, int indexRow, int i) {
        return indexColumn + i < this.n && indexColumn + i >= 0 && indexRow + i < this.m && indexRow + i >= 0;
    }

    private void checkK(int l) {
        if (CountL[l] < this.k) {
            CountL[l] = 0;
        }
    }

    private void increase(int i, int l, int r, int f) {
        if (i < 0) {
            Lines[l] += 1;
        } else if (i > 0 && Lines[f] == 1) {
            Lines[r] += 1;
        }
    }

    private void checkerLine(int i, int l, int f) {
        if (i < 0) {
            Lines[l] = 0;
        } else if (i > 0) {
            Lines[f] = 0;
        }
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell[][] getPositionNew() {
        Cell[][] copyCell = new Cell[this.m][this.n];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                copyCell[i][j] = this.cells[i][j];
            }
        }
        return copyCell;
    }

    @Override
    public Cell getCellNew() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        // :NOTE: а что, если схложу X вмето 0
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        CountL[0] = 0;
        CountL[1] = 0;
        CountL[2] = 0;
        CountL[3] = 0;
        int indexRow = move.getRow();
        int indexColumn = move.getColumn();
        Lines[0] = 0;
        Lines[1] = 0;
        Lines[2] = 1;
        Lines[3] = 0;
        Lines[4] = 0;
        Lines[5] = 1;
        Lines[6] = 0;
        Lines[7] = 0;
        Lines[8] = 1;
        Lines[9] = 0;
        Lines[10] = 0;
        Lines[11] = 1;
        countMove += 1;
        for (int i = -this.k + 1; i < this.k; i++) {
            if (indexRow + i >= 0 && indexRow + i < this.m) {
                if (cells[indexRow + i][indexColumn] == turn) {
                    CountL[1] += 1;
                    increase(i, 0, 1, 2);
                } else {
                    checkK(1);
                    checkerLine(i, 0, 2);

                }
            }
            if (indexColumn + i >= 0 && indexColumn + i < this.n) {
                if (cells[indexRow][indexColumn + i] == turn) {
                    CountL[0] += 1;
                    increase(i, 3, 4, 5);
                } else {
                    checkK(0);
                    checkerLine(i, 3, 5);
                }
            }
            if (Diag(indexColumn, indexRow, i)) {
                if (cells[indexRow + i][indexColumn + i] == turn) {
                    CountL[2] += 1;
                    increase(i, 6, 7, 8);
                } else {
                    checkK(2);
                    checkerLine(i, 6, 8);
                }
            }
            if (Diag(indexColumn - 2 * i, indexRow, i)) {
                if (cells[indexRow + i][indexColumn - i] == turn) {
                    CountL[3] += 1;
                    increase(i, 9, 10, 11);
                } else {
                    checkK(3);
                    checkerLine(i, 9, 11);
                }
            }
        }
        if (CountL[2] >= k || CountL[3] >= k || CountL[0] >= k || CountL[1] >= k) {
            return Result.WIN;
        }
        if (countMove == this.siz) {
            return Result.DRAW;
        }
        for (int i = 0; i < 11; i++) {
            if (Lines[i] > 3) {
                Lines[i] = 0;
            }
        }
        if (!((Lines[0] + Lines[1]) >= 3 || (Lines[3] + Lines[4]) >= 3 || (Lines[6] + Lines[7]) >= 3
                || (Lines[10] + Lines[9]) >= 3)) {
            turn = turn == Cell.X ? Cell.O : Cell.X;
        }

        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < this.m
                && 0 <= move.getColumn() && move.getColumn() < this.n
                && cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }
}
