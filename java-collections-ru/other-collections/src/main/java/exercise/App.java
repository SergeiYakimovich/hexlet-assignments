package exercise;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

// BEGIN
public class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> result = new TreeMap<>(Comparator.comparing(k -> k));
        for (Map.Entry<String, Object> data: data1.entrySet()) {
            String key = data.getKey();
            Object value1 = data.getValue();
            if (data2.containsKey(key)) {
                Object value2 = data2.get(key);
                if (value1.equals(value2)) {
                    result.put(key, "unchanged");
                } else {
                    result.put(key, "changed");
                }
            } else {
                result.put(key, "deleted");
            }
        }
        for (Map.Entry<String, Object> data: data2.entrySet()) {
            String key = data.getKey();
            if (!data1.containsKey(key)) {
                result.put(data.getKey(), "added");
            }
        }

        return  new LinkedHashMap<>(result);
    }

}
//END
