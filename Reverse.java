import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    private static int[] alloc(int[] arr) {
        int[] nw = Arrays.copyOf(arr, 2 * arr.length);
        return nw;
    }

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[] chisla = new int[10];
        int[] graniza = new int[10];
        graniza[0] = -1;
        int schetchikChisel = 0;
        int schetchikStrok = 1;
        try {
            while (input.ready()) {
                Scanner stroka = new Scanner(input.readLine());
                if (schetchikStrok + 1 > graniza.length) {
                    graniza = alloc(graniza);
                }
                while (stroka.hasNextInt()) {
                    if (schetchikChisel + 1 > chisla.length) {
                        chisla = alloc(chisla);
                    }
                    chisla[schetchikChisel] = stroka.nextInt();
                    schetchikChisel++;
                }
                graniza[schetchikStrok] = schetchikChisel - 1;
                schetchikStrok++;
            }
        } catch (IOException e) {
            System.err.println("Error IOE");
        }
        // в границе есть левый элемент 0 поэтому счестик строк начинается с 1 и форик до i > 0
        for (int i = schetchikStrok - 1; i > 0; i--) {
            for (int j = graniza[i]; j > graniza[i - 1]; j--) {
                System.out.print(chisla[j] + " ");
            }
            System.out.println();
        }
    }
}
