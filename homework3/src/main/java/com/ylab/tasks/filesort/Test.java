package homework3.src.main.java.com.ylab.tasks.filesort;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        int count = 10_000_000;
        File dataFile = new Generator().generate("data.txt", count);
        System.out.printf("Is the file sorted before sorting? - %s %n",
                new Validator(dataFile).isSorted());

        long startTime = System.currentTimeMillis();
        File sortedFile = new Sorter().sortFile(dataFile);
        System.out.printf("Took time for sorting for %s lines file: about %d sec.%n",
                count, (System.currentTimeMillis() - startTime) / 1000);

        System.out.printf("Is the file sorted after sorting? - %s %n",
                new Validator(sortedFile).isSorted());
    }
}
