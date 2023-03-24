package homework3.src.main.java.com.ylab.tasks.transliterator;

import static homework3.src.main.java.com.ylab.tasks.transliterator.CyrillicToLatinMap.cyrillicToLatinMap;

public class TransliteratorImpl implements Transliterator {

    @Override
    public String transliterate(String source) {
        StringBuilder transliteratedSource = new StringBuilder();
        if (source == null || source.isEmpty()) {
            return transliteratedSource.toString();
        }
        for (int i = 0; i < source.length(); i++) {
            String currentLetter = String.valueOf(source.charAt(i));
            transliteratedSource.append(
                    cyrillicToLatinMap.getOrDefault(currentLetter, currentLetter)
            );
        }
        return transliteratedSource.toString();
    }
}
