package exercise;

import java.nio.file.Path;
import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws Exception {
        String text = car.serialize();
        writeString(path, text);
    }

    public static Car extract(Path path) throws Exception {
        String text = readString(path);
        return Car.unserialize(text);
    }

}
// END
