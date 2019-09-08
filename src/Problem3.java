import java.nio.file.*;
import java.util.*;

public class Problem3 {
    private static final String PATH_NAME = "src/test.txt";
    private static final int RANKING = 10;

    public static void main(String[] args) {
        try {
            var path = Paths.get(PATH_NAME);
            var lines = Files.readAllLines(path);

            var counterMap = new HashMap<String, Integer>();
            lines.forEach(line -> countWordPerLine(counterMap, line));

            showWordsOfFrequencyInSentence(counterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void countWordPerLine(Map<String, Integer> counterMap, String line) {
        var words = line.split("[\\W]+");
        for (var word: words) {
            counterMap.compute(word, (k, count) -> count == null ? 1 : count + 1);
        }
    }

    private static void showWordsOfFrequencyInSentence(Map<String, Integer> counterMap) {
        // @formatter:off
        counterMap.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(RANKING)
            .forEach(result -> System.out.println(result.getKey() + " " + result.getValue()));
        // @formatter:on
    }
}
