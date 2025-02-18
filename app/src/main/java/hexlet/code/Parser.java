package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseJsonToMap(String path) throws IOException {
        File file = Paths.get(path).normalize().toAbsolutePath().toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<Map<String, Object>>() { });
    }
}
