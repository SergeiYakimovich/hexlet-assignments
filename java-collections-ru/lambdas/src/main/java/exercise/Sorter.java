package exercise;

import java.util.ArrayList;
//import java.util.Comparator;
import java.util.Map;
import java.util.List;
//import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        List<String> result = new ArrayList<>();
        result = users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted((birthday1, birthday2) -> birthday1.get("birthday").compareTo(birthday2.get("birthday")))
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
        return result;
    }

}
// END
