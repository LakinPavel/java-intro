package game;

import java.util.Scanner;

import game.CircleBoard;
import game.Game;
import game.HumanPlayer;
import game.Main;
import game.Player;
import game.RectangleBoard;

/*
лишнее место
 */

public class Main {
    Player[] players;
    Integer[] playersInGame;
    int countGames;
    int maxSize2;
    int participants;
    Integer[] place;
    int m;
    int n;
    int k;
    int b;
    int d;

    public void competition(int j) {
        System.out.println("Game " + countGames + " Players: " + (j + 1) + " " + (j + maxSize2 + 1));
        final Game game = new Game(false, players[j], players[j + maxSize2]);
        int result;
        do {
            if (b == 0) {
                result = game.play(new RectangleBoard(m, n, k));
            } else {
                result = game.play(new CircleBoard(d, k));
            }
            System.out.println("Game result: " + result);
        } while (result == 0);
        if (result == 2) {
            place[participants - countGames - 1] = j;
            playersInGame[j] = j + maxSize2;
        } else {
            place[participants - countGames - 1] = j + maxSize2;

        }
        countGames += 1;
    }

    public static void main(String[] args) {
        Main newMain = new Main();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of participants");
        int flagInputSize = 0;
        do {
            if (in.hasNextInt()) {
                newMain.participants = in.nextInt();
                flagInputSize = 1;
            } else {
                in.nextLine();
                System.out.println("Incorrect data, you should enter numbers, please try again");
            }
        } while (flagInputSize == 0);
        newMain.players = new Player[newMain.participants];
        newMain.place = new Integer[newMain.participants];
        newMain.playersInGame = new Integer[newMain.participants];
        for (int i = 0; i < newMain.participants; i++) {
            newMain.playersInGame[i] = i;
            newMain.players[i] = new HumanPlayer();
        }
        int t = newMain.participants;
        int power = 0;
        newMain.maxSize2 = 1;
        while (t != 1) {
            t /= 2;
            newMain.maxSize2 *= 2;
            power += 1;
        }
        int maxPower2 = newMain.maxSize2;

        newMain.b = -1;
        System.out.println("Enter which board you want rectangle or circle");
        while (newMain.b == -1) {
            String BoardType = in.nextLine();
            if (BoardType.equals("circle")) {
                newMain.b = 1;
            } else if (BoardType.equals("rectangle")) {
                newMain.b = 0;
            } else if (!BoardType.isEmpty()) {
                System.out.println("Incorrect data entered, please try again");
            }
        }

        if (newMain.b == 0) {
            System.out.println("Enter m, n, k");
            flagInputSize = 0;
            do {
                if (in.hasNextInt()) {
                    newMain.m = in.nextInt();
                    newMain.n = in.nextInt();
                    newMain.k = in.nextInt();
                    flagInputSize = 1;

                } else {
                    in.nextLine();
                    System.out.println("Incorrect data, you should enter numbers, please try again");
                }
            } while (flagInputSize == 0);

            newMain.countGames = 0;
            for (int i = 0; i < newMain.participants - newMain.maxSize2; i++) {
                newMain.competition(i);
            }
            newMain.maxSize2 /= 2;

            while (newMain.maxSize2 > 0) {
                for (int i = 0; i < newMain.maxSize2; i++) {
                    newMain.competition(i);
                }
                newMain.maxSize2 /= 2;
            }

        } else {
            flagInputSize = 0;
            System.out.println("Enter d, k");
            do {
                if (in.hasNextInt()) {
                    newMain.d = in.nextInt();
                    newMain.k = in.nextInt();
                    flagInputSize = 1;
                } else {
                    in.nextLine();
                    System.out.println("Incorrect data, you should enter numbers, please try again");
                }
            } while (flagInputSize == 0);

            newMain.countGames = 0;
            for (int i = 0; i < newMain.participants - newMain.maxSize2; i++) {
                newMain.competition(i);
            }
            newMain.maxSize2 /= 2;

            while (newMain.maxSize2 > 0) {
                for (int i = 0; i < newMain.maxSize2; i++) {
                    newMain.competition(i);
                }
                newMain.maxSize2 /= 2;
            }

        }
        newMain.place[0] = newMain.playersInGame[0];
        System.out.println("Places");
        System.out.println("1: " + (newMain.place[0] + 1));
        System.out.println("2: " + (newMain.place[1] + 1));
        int placeCounter = 3;
        int l = 2;
        while (l * 2 < newMain.participants) {
            StringBuilder pl = new StringBuilder();
            for (int i = l; i < l * 2; i++) {
                pl.append(" ");
                pl.append((newMain.place[i] + 1));
            }
            System.out.println(placeCounter + ":" + pl);
            l *= 2;
            placeCounter += 1;
        }
        StringBuilder lastPlace = new StringBuilder();
        for (int i = maxPower2; i < newMain.participants; i++) {
            lastPlace.append(" ");
            lastPlace.append((newMain.place[i] + 1));
        }
        if (!lastPlace.isEmpty()) {
            System.out.println(placeCounter + ":" + lastPlace);
        }
    }
}
