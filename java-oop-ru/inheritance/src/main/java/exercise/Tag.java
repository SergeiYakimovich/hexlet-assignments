package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {
    private String name;
    private Map<String, String> dictionary;

    public Tag(String newName, Map<String, String> newDictionary) {
        name = newName;
        dictionary = newDictionary;
    }

    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("<" + getName());
        for (Map.Entry<String, String> item: getDictionary().entrySet()) {
            resultStr.append(" " + item.getKey() + "=\"" + item.getValue() + "\"");
        }
        return resultStr.append(">").toString();
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

}
// END
