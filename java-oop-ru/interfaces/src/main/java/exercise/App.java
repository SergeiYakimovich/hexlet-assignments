package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static List<String> buildAppartmentsList(List<Home> appartments, Integer numberOfHomes) {
        List<String> result = new ArrayList<>();
        appartments.stream()
                .sorted((item1, item2) -> Double.compare(item1.getArea(), item2.getArea()))
                .limit(numberOfHomes)
                .forEach(item -> result.add(item.toString())
                );
        return result;
    }
}
// END
