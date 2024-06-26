import java.util.*;

import static java.lang.Math.abs;

public class Main {
    public static Random random;
    public static Scanner scanner;

    public static String[][] board;
    public static HashMap<String, String> dictionary;
    public static List<String> givenList = Arrays.asList("В_негрет", "Тро_ейбус", "Здра_ствуйте", "Вы_грать", "Параш_т", "Ж_ри", "Рю_зак", "Ай_берг", "Ветре_ый", "Стекля_ый");
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
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        System.out.println("Решите пример " + a + " + " + b + " = ");
        int answer = scanner.nextInt();
        if (answer == (a + b)) return true;
        else return false;
    }

    public static boolean giveBigTask() {
        String randomElement = givenList.get(random.nextInt(givenList.size()));
        System.out.println(randomElement);
        scanner = new Scanner(System.in);
        String ans = scanner.nextLine().toLowerCase();
        ans = dictionary.get(ans);
        if (randomElement.equals(ans)) return true;
        else return false;
    }

    public static void main(String[] args) {
        int size = 5;
        personX = 0;
        personY = 4;
        int personLive = 3;
        dictionary = new HashMap<>();
        dictionary.put("и", "В_негрет");
        dictionary.put("лл", "Тро_ейбус");
        dictionary.put("в", "Здра_ствуйте");
        dictionary.put("ю", " Параш_т");
        dictionary.put("к", "Рю_зак");
        dictionary.put("с", " Ай_берг");
        dictionary.put("н", "Ветре_ый");
        dictionary.put("нн", "Стекля_ый");
        int count_monster = size * size - size - 1;
        int count_bigmonster = 4;
        random = new Random();
        int castleX = 1 + random.nextInt(size - 1);
        int castleY = 0;
        String person = "\uD83E\uDDD9";
        String bigmonster = "\uD83E\uDDDF";
        String monster = "\uD83E\uDDB9";
        String castle = "\uD83C\uDFF0";
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";

        scanner = new Scanner(System.in);

        board = new String[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = "  ";
            }
        }
        for (int i = 0; i <= count_monster; i++) {
            board[random.nextInt(size)][random.nextInt(size)] = monster;
        }
        for (int i = 0; i <= count_bigmonster; i++) {
            board[random.nextInt(size)][random.nextInt(size)] = bigmonster;
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


            movePerson(x, y);
            if (board[y][x].equals(monster)) {
                if (!giveTask()) {
                    personLive--;
                    System.out.println("Вы потеряли жизнь");
                } else System.out.println("Вы победили монстра");
            } else if (board[y][x].equals(bigmonster)) {
                if (!giveBigTask()) {
                    personLive -= 2;
                    System.out.println("Вы потеряли две жизни");
                } else System.out.println("Вы победили большого монстра");

            }
        }
        if (personLive >= 0) System.out.println("Вы прошли игру!");
        else System.out.println("Вы проиграли");

    }
}