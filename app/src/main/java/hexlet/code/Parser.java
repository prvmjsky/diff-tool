package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static String readFile(String path) throws IOException {
        var normalizedPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(normalizedPath);
    }

    public static String getFileType(String path) throws IOException {
        if (path.endsWith(".json")) {
            return "json";
        } else if (path.endsWith(".yml") || path.endsWith(".yaml")) {
            return "yaml";
        } else {
            throw new IOException("wrong file extension");
        }
    }

    public static Map<String, Object> toMap(String data, String fileType) throws JsonProcessingException {

        ObjectMapper mapper;

        if (fileType.equals("json")) {
            mapper = new ObjectMapper();
        } else if (fileType.equals("yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("wrong file type");
        }

        return mapper.readValue(data, new TypeReference<>() {
        });
    }
}
