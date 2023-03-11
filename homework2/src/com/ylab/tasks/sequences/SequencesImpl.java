package homework2.src.com.ylab.tasks.sequences;

import java.util.stream.Stream;

public class SequencesImpl implements Sequences {

    @Override
    public void a(int n) {
        // A. 2, 4, 6, 8, 10...
        System.out.print("A. ");
        Stream.iterate(2, number -> number + 2)
                .limit(n)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void b(int n) {
        // B. 1, 3, 5, 7, 9...
        System.out.print("B. ");
        Stream.iterate(1, number -> number + 2)
                .limit(n)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void c(int n) {
        // C. 1, 4, 9, 16, 25...
        System.out.print("C. ");
        Stream.iterate(1, number -> number + 1)
                .limit(n)
                .map(number -> number * number)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void d(int n) {
        // D. 1, 8, 27, 64, 125...
        System.out.print("D. ");
        Stream.iterate(1, number -> number + 1)
                .limit(n)
                .map(number -> (int) Math.pow(number, 3))
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void e(int n) {
        // E. 1, -1, 1, -1, 1, -1...
        System.out.print("E. ");
        Stream.iterate(1, number -> 1)
                .flatMap(number -> Stream.of(number, -1))
                .limit(n)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void f(int n) {
        // F. 1, -2,  3,  -4,   5,  -6...
        System.out.print("F. ");
        Stream.iterate(1, number -> number + 1)
                .limit(n)
                .map(number -> number % 2 == 0 ? number * -1 : number)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void g(int n) {
        // G. 1, -4,  9, -16,  25....
        System.out.print("G. ");
        Stream.iterate(1, number -> number + 1)
                .limit(n)
                .map(number -> number * number)
                .map(number -> number % 2 == 0 ? number * -1 : number)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void h(int n) {
        // H. 1, 0, 2, 0, 3, 0, 4....
        System.out.print("H. ");
        Stream.iterate(1, number -> number + 1)
                .flatMap(number -> Stream.of(number, 0))
                .limit(n)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Override
    public void i(int n) {
        // I. 1, 2, 6, 24, 120, 720, 5040...
        System.out.print("I. ");
        int multiplyResult = 1;
        for (int i = 1; i <= n; i++) {
            multiplyResult *= i;
            System.out.print(multiplyResult + " ");
        }
        System.out.println();
    }

    @Override
    public void j(int n) {
        // J. 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89...
        System.out.print("J. ");
        int firstNumber = 0;
        int secondNumber = 1;
        int result = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(result + " ");
            result = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = result;
        }
        System.out.println();
    }
}
