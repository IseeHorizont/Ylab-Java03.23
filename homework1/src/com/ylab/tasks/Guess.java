package homework1.src.com.ylab.tasks;

import java.util.Random;
import java.util.Scanner;

public class Guess {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int number = new Random().nextInt(99) + 1; // здесь загадывается число от 1 до 99
        int maxAttempts = 10; // здесь задается количество попыток

        // TODO cheat
        System.out.println(number);

        System.out.println(String.format("Я загадал число от 1 до 99. У тебя %s попыток угадать.",
                maxAttempts));

        guessGame(number, maxAttempts);
    }

    public static void guessGame(int number, int maxAttempts) {
        int attemptCounter = 1;
        boolean isGuessed = false;
        while (attemptCounter <= maxAttempts) {
            System.out.println("Введи своё число: ");
            int numberFromUser = scanner.nextInt();
            int attemptsLeft = maxAttempts - attemptCounter;

            if (numberFromUser < 1 || numberFromUser >= 100) {
                System.out.println(String.format("Твоё число неверное. Осталось %s попыток", attemptsLeft));
            } else if (numberFromUser == number) {
                System.out.println(String.format("Ты угадал с %s попытки", attemptCounter));
                isGuessed = true;
                break;
            } else if (numberFromUser > number) {
                System.out.println(String.format("Моё число меньше! Осталось %s попыток", attemptsLeft));
            } else {
                System.out.println(String.format("Моё число больше! Осталось %s попыток", attemptsLeft));
            }
            attemptCounter++;
        }
        if (!isGuessed) {
            System.out.println("Ты не угадал");
        }
    }

}
