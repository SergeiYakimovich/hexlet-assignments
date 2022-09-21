package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static String getForwardedVariables(String content) {

        return Arrays.stream(content.split("\n"))
                .filter(line -> line.startsWith("environment=") && line.contains("X_FORWARDED_"))
                .map(line -> line.replaceAll("environment=", ""))
                .map(line -> line.replaceAll("\"", ""))
                .map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .filter(name -> name.startsWith("X_FORWARDED_"))
                .map(name -> name.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));

    }

}
//END
