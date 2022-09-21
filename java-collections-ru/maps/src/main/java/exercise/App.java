package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordsAndCount = new HashMap<>();
        if (sentence.length() == 0) {
            return wordsAndCount;
        }
        String[] words = sentence.split(" ");
        for (String word : words) {
            if (wordsAndCount.containsKey(word)) {
                int count = wordsAndCount.get(word);
                count++;
                wordsAndCount.put(word, count);

            } else {
                wordsAndCount.put(word, 1);
            }
        }

        return wordsAndCount;
    }

    public static String toString(Map<String, Integer> wordsCount) {
        if (wordsCount.size() == 0) {
            return "{}";
        }
        String result = "{";
        for (Map.Entry<String, Integer> word: wordsCount.entrySet()) {
            result = result + "\n  " + word.getKey() + ": " + word.getValue();
        }
        result = result + "\n}";
        return result;
    }

}
//END
