package homework1.src.com.ylab.tasks;

/**
 * Задача 3. На вход ничего не подается,
 * необходимо распечатать таблицу умножения чисел от 1 до 9 (включая)
 */
public class MultTable {

    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <=9 ; j++) {
                System.out.println(String.format("%s x %s = %s", i, j, i * j));
            }
            System.out.println();
        }
    }
}
