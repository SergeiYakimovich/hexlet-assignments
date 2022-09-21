package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> newStorage = new HashMap<>();
        for (Map.Entry<String, String> item: storage.toMap().entrySet()) {
            newStorage.put(item.getValue(), item.getKey());
            storage.unset(item.getKey());
        }
        for (Map.Entry<String, String> item: newStorage.entrySet()) {
            storage.set(item.getKey(), item.getValue());
        }
    }

}
// END
