package homework1.src.com.ylab.tasks;

import java.util.Scanner;

/**
 *  Задача 1. Программе передается 3 параметра: количество строк, количество столбцов, произвольный символ.
 *  Необходимо вывести вывести фигуру, состоящую из заданного списка строк и заданного количества столбцов,
 *  и каждый элемент в которой равен указанному символу.
 */
public class Stars {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.next();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(template + " ");
                }
                System.out.println();
            }
        }

    }


}
