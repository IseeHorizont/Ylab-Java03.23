package homework3.src.main.java.com.ylab.tasks.transliterator;

import java.util.stream.Stream;

public class TransliteratorTest {

    public static void main(String[] args) {
        Transliterator transliterator = new TransliteratorImpl();

        getRightTestData().forEach(
                item -> System.out.printf("Source line: '%s'.%nLine after transliteration: '%s'.%n",
                        item, transliterator.transliterate(item))
        );

        getWrongTestData().forEach(
                item -> System.out.printf("Source line: '%s'.%nLine after transliteration: '%s'.%n",
                        item, transliterator.transliterate(item))
        );
    }

    public static Stream<String> getRightTestData() {
        return Stream.of(
                "HELLO! ПРИВЕТ! Go, boy!",
                "ФAМУСОВ, ТУГОУХОВСКИЙ & СОБАКЕВИЧ ARE NOT MY FRIENDS",
                "WHAT IS БУЛЬБА? MAYBE HE'S A СКАЛОЗУБ",
                "HI, I'M СЕМЁНОВ-ПИЩИК"
        );
    }

    public static Stream<String> getWrongTestData() {
        return Stream.of(
                null,
                "null",
                "",
                " "
        );
    }
}
