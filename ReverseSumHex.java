import java.lang.Integer;
import static java.lang.Math.max;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseSumHex {
    private static int[] alloc(int[] argument) {
        int[] newList = Arrays.copyOf(argument, 2 * argument.length);
        return newList;
    }

    private static int[][] alloc2(int[][] argument) {
        int[][] newList = Arrays.copyOf(argument, 2 * argument.length);
        return newList;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] matrixInput = new int[10][];
        int[] nulLine = new int[10];
        matrixInput[0] = nulLine;
        int countLines = 1;
        int maxLineLenght = 0;
        while (input.hasNextLine()) {
            if (countLines + 1 > matrixInput.length) {
                matrixInput = alloc2(matrixInput);
            }
            int lineLenght = 1;
            int[] Line = new int[10];
            Scanner t = new Scanner(input.nextLine());
            while (t.hasNext()) {
                if (lineLenght + 2 > Line.length) {
                    Line = alloc(Line);
                }
                Line[lineLenght] = Integer.parseUnsignedInt(t.next(), 16);
                lineLenght++;
            }
            maxLineLenght = max(maxLineLenght, lineLenght);
            Line[Line.length - 1] = lineLenght;
            matrixInput[countLines] = Line;
            countLines++;
        }
        int[][] dpMatrix = new int[2][maxLineLenght];
        for (int i = 1; i < countLines; i++) {
            for (int j = 1; j < maxLineLenght; j++) {
                if (j < matrixInput[i][matrixInput[i].length - 1]) {
                    dpMatrix[(i + 1) % 2][j] = matrixInput[i][j] + dpMatrix[i % 2][j] + dpMatrix[(i + 1) % 2][j - 1] - dpMatrix[i % 2][j - 1];
                    System.out.print(Integer.toHexString(dpMatrix[(i + 1) % 2][j]) + " ");
                } else {
                    dpMatrix[(i + 1) % 2][j] = dpMatrix[i % 2][j] + dpMatrix[(i + 1) % 2][j - 1] - dpMatrix[i % 2][j - 1];
                }
            }
            System.out.println();
        }
        input.close();
    }
}
