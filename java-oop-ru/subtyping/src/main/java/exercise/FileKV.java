package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String filePath;

    public FileKV(Map<String, String> dataValue, String filePathValue) {
        Map<String, String> data = new HashMap<>();
        data.putAll(dataValue);
        this.filePath = filePathValue;
        writeData(data);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = readData();
        data.put(key, value);
        writeData(data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = readData();
        data.remove(key);
        writeData(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = readData();
        if (data.containsKey(key)) {
            return data.get(key);
        } else {
            return defaultValue;
        }
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(readData());
    }

    public void writeData(Map<String, String> data) {
        String result = Utils.serialize(data);
        Utils.writeFile(filePath, result);
    }

    public Map<String, String> readData() {
        String result = Utils.readFile(filePath);
        return Utils.unserialize(result);
    }

}
// END
