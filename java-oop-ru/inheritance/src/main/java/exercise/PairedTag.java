package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {
    private String text;
    private List<Tag> list;


    public PairedTag(String newName, Map<String, String> newDictionary, String newText,
                     List<Tag> newList) {
        super(newName, newDictionary);
        text = newText;
        list = newList;
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append(super.toString());
        resultStr.append(text);
        list.stream()
                .forEach(item -> resultStr.append(item.toString()));
        return resultStr.append("</" + getName() + ">").toString();
    }

}
// END
