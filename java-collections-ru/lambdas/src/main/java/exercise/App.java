package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {
    private static int row;
    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] result = new String[image.length * 2][image[0].length * 2];
        row = 0;
        Arrays.stream(image)
                .map(x -> Arrays.stream(x)
                        .map(y -> new String[] {y, y})
                        .toArray(String[][]::new))
                .map(x -> Arrays.stream(x)
                                .flatMap(Stream::of)        // Stream<String>
                                .toArray(String[]::new))
                .forEach(x -> {
                    result[row++] = x;
                    result[row++] = x;
                });

        return result;

    }

}

// END
