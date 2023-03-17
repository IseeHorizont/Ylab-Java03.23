package homework2.src.main.java.com.ylab.tasks.snils;

import java.util.stream.Stream;

public class SnilsValidatorTest {

    public static void main(String[] args) {

        // Valid snils
        Stream.of("90114404441", "19193989134", "799-639-885-27", "554 764 742 30")
                .forEach(snils -> System.out.println(String.format("Valid snils: '%s' = %s\n",
                        snils, new SnilsValidatorImpl().validate(snils))));

        // Not valid snils
        Stream.of("01468870570", " ", "19190989134", "799-639-885-97", "304 764 742 55", "Q919t891393")
                .forEach(snils -> System.out.println(String.format("Valid snils: '%s' = %s\n",
                        snils, new SnilsValidatorImpl().validate(snils))));
    }
}
