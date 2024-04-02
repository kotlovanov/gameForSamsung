import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    public static Random random;
    public static Scanner scanner;

    public static String[][] board;
    public static HashMap<String, String> dictionary;
    public static int step = 0;
    public static int personX, personY;

    public static void movePerson(int newX, int newY) {
        board[personY][personX] = "  ";
        personX = newX;
        personY = newY;
        step++;
        System.out.println("Ход корректный; Новые координаты: " + (personX + 1) + ", " + (personY + 1) +
                "\nХод номер: " + step);
    }

    public static boolean giveTask() {
        return true;
    }

    public static void main(String[] args) {
        int size = 5;
        int a = 9;
        personX = 0;
        personY = 4;
        int personLive = 3;
        int count_monster = size * size - size - 1;
        random = new Random();
        int castleX = 1 + random.nextInt(size - 1);
        int castleY = 0;
        String person = "Г ";
        String monster = "Мм";
        String castle = "\uD83C\uDFF0";
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";

//        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        scanner = new Scanner(System.in);
//        String answer = scanner.nextLine();
//        switch (answer) {
//            case "ДА": {
//                break;
//            }
//            case "НЕТ": {
//                System.out.println("Жаль, приходи еще!");
//                break;
//            }
//            default: {
//                System.out.println("Данные введены некорректно");
//            }
//        }
        board = new String[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = "  ";
            }
        }
        for (int i = 0; i <= count_monster; i++) {
            board[random.nextInt(size)][random.nextInt(size)] = monster;
        }

        while (personLive > 0 && !(castleX == personX && castleY == personY)) {

            board[castleY][castleX] = castle;
            board[personY][personX] = person;
            board[castleY][castleX] = castle;
            board[personY][personX] = person;
            for (int y = 0; y < size; y++) {
                System.out.println(wall);
                for (int x = 0; x < size; x++) {
                    System.out.print(leftBlock);
                    System.out.print(board[y][x]);
                }
                System.out.println(rightBlock);
            }


            System.out.println(wall);
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            if (!(abs(personX - x) <= 1 && abs(personY - y) <= 1 && abs(personX - x) != abs(personY - y))) continue;


//            (board[(personY - 1) * size + personX - 1].equals("  "))
            movePerson(x, y);
            if (board[y][x].equals(monster)) {
                if (!giveTask()) {
                    personLive--;
                    System.out.println("Вы потеряли жизнь");
                } else System.out.println("Вы победили монстра");
            } else {
                System.out.println("Решите задачу.");
            }

        }
        if (personLive != 0) System.out.println("Вы прошли игру!");
        else System.out.println("Вы проиграли");
    }
}