package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import game.Cell;
import game.Move;
import game.Player;

public class HumanPlayer implements Player {
    protected static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.Out, ' ');
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Cell[][] board, final Cell cell) {
        while (true) {
            out.println("Position");
            int sizeM = Integer.toString(board.length).length();
            int sizeN = Integer.toString(board[0].length).length();
            final StringBuilder sb = new StringBuilder();
            sb.append(" ".repeat(sizeM)).append("|");
            sb.append(" ".repeat(sizeN - Integer.toString(0).length())).append(0);
            for (int i = 1; i < board[0].length; i++) {
                int diffrent = sizeN - Integer.toString(i).length() + 1;
                sb.append(" ".repeat(diffrent)).append(i);
            }
            sb.append(System.lineSeparator());
            sb.append("-".repeat(sizeM)).append("+");
            for (int i = 0; i < board[0].length; i++) {
                sb.append("-".repeat(sizeN + 1));
            }
            for (int r = 0; r < board.length; r++) {
                int lenR = Integer.toString(r).length();
                sb.append(System.lineSeparator());
                sb.append(" ".repeat(sizeM - lenR)).append(r).append("|");
                for (int c = 0; c < board[0].length; c++) {
                    sb.append(" ".repeat(sizeN - 1)).append(SYMBOLS.get(board[r][c]) + " ");
                }
            }
            System.out.println(sb.toString());
            out.println(cell + "'s move");
            int firstNumber = 0;
            int secondNumber = 0;
            out.println("Please, enter Row and Colomn");
            int flag = 0;
            while (flag == 0) {
                try {
                    firstNumber = in.nextInt();
                    secondNumber = in.nextInt();
                    flag = 1;
                } catch (InputMismatchException e) {
                    out.println("Incorrect data, you should enter numbers, please try again enter Row and Colom");
                    in.nextLine();
                }
            }
            final Move move = new Move(firstNumber, secondNumber, cell);
            if (0 <= move.getRow() && move.getRow() < board.length
                    && 0 <= move.getColumn() && move.getColumn() < board[0].length
                    && board[move.getRow()][move.getColumn()] == Cell.E) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
