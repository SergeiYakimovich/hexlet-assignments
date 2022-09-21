package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Getter
@AllArgsConstructor
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public static Car unserialize(String str) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str, Car.class);
    }
    // END
}
