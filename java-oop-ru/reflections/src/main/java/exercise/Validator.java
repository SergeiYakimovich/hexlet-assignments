package exercise;

import javax.sql.rowset.Predicate;
import java.beans.Expression;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    interface LambdaExpression {
        boolean isEqual(String s, MinLength n);
    }

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        LambdaExpression func = (s,n)-> s==null;
        for (Field field : address.getClass().getDeclaredFields()) {
            if (notValid(field, address, NotNull.class, func)) {
                result.add(field.getName());
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidFields = new HashMap<>();
        validate(address).stream()
                .forEach(item -> notValidFields.put(item, List.of("can not be null")));
        LambdaExpression func = (s, n)-> s== null || s.length() < n.minLength() ;
        for (Field field : address.getClass().getDeclaredFields()) {
            if (notValid(field, address, MinLength.class, func)) {
                addMap(notValidFields, field.getName(), "length less than "
                        + field.getAnnotation(MinLength.class).minLength());
            }
        }
        return notValidFields;
    }

    private static boolean notValid(Field field, Address address, Class annotationClass, LambdaExpression func) {
        Annotation fieldAnnotation = field.getAnnotation(annotationClass);
        if (fieldAnnotation == null) {
            return false;
        }
        try {
            field.setAccessible(true);
            String value = (String) field.get(address);
            if (func.isEqual(value, field.getAnnotation(MinLength.class))) {
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void addMap(Map<String, List<String>> notValidFields, String key, String str) {
        if (notValidFields.containsKey(key)) {
            List<String> newList = new ArrayList<>(notValidFields.get(key));
            newList.add(str);
            notValidFields.put(key, newList);
        } else {
            notValidFields.put(key, List.of(str));
        }
    }

}
// END
