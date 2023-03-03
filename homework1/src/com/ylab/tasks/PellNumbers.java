package homework1.src.com.ylab.tasks;

import java.util.Scanner;

/**
 *  Задача 2. На вход подается число n (0 <= n <= 30),
 *  необходимо распечатать n-e число Пелля
 */
public class PellNumbers {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = 0;
            do {
                System.out.print("Введите число от 0 до 30: ");
                n = scanner.nextInt();
            } while (n < 0 || n > 30);

            if (n == 0) {
                System.out.println("0-e число Пелля: 0");
            } else if (n == 1) {
                System.out.println("1-e число Пелля: 1");
            } else {
                long n0 = 0;
                long n1 = 1;
                long result = 0;
                for (int i = 2; i <= n; i++) {
                    result = 2 * n1 + n0;
                    //System.out.println(i + " -> " + result);
                    n0 = n1;
                    n1 = result;
                }
                System.out.println(String.format("%s-e число Пелля: %s", n, result));
            }
        }
    }
}
