package homework3.src.main.java.com.ylab.tasks.transliterator;

import java.util.HashMap;
import java.util.Map;

public class CyrillicToLatinMap {

    public static Map<String, String> cyrillicToLatinMap = new HashMap<>() {{
        put("А", "A");
        put("Б", "B");
        put("В", "V");
        put("Г", "G");
        put("Д", "D");
        put("Е", "E");
        put("Ё", "E");
        put("Ж", "ZH");
        put("З", "Z");
        put("И", "I");
        put("Й", "I");
        put("К", "K");
        put("Л", "L");
        put("М", "M");
        put("Н", "N");
        put("О", "O");
        put("П", "P");
        put("Р", "R");
        put("С", "S");
        put("Т", "T");
        put("У", "U");
        put("Ф", "F");
        put("Х", "KH");
        put("Ц", "TS");
        put("Ч", "CH");
        put("Ш", "SH");
        put("Щ", "SHCH");
        put("Ы", "Y");
        put("Ь", "");
        put("Ъ", "IE");
        put("Э", "E");
        put("Ю", "IU");
        put("Я", "IA");
    }};
}
