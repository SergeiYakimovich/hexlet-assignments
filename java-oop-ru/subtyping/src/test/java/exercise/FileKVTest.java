package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void testFileKV() {
        Map<String, String> initial = new HashMap<>();
        initial.put("key", "10");
        initial.put("key2", "value2");
        KeyValueStorage storage = new FileKV(initial, filepath.toString());
        Map<String, String> result = Utils.unserialize(Utils.readFile(filepath.toString()));
        assertThat(result).isEqualTo(initial);

        initial.put("key3", "value3");
        storage.set("key3", "value3");
        result = Utils.unserialize(Utils.readFile(filepath.toString()));
        assertThat(result).isEqualTo(initial);

        initial.remove("key");
        storage.unset("key");
        result = Utils.unserialize(Utils.readFile(filepath.toString()));
        assertThat(result).isEqualTo(initial);

    }
    // END
}
