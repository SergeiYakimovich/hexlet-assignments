package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String inputText, String inputWord) {
        var text = inputText.toLowerCase().trim();
        var word = inputWord.toLowerCase().trim();

        List<Character> list = new ArrayList<>();
        for (char item : text.toCharArray()) {
            list.add(item);
        }
        for (int i = 0; i < word.length(); i++) {
            Character nextSymbol = word.charAt(i);
            if (list.contains(nextSymbol)) {
                list.remove(nextSymbol);
            } else {
                return false;
            }
        }
        return true;
    }

}
//END
