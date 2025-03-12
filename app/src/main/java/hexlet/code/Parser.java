package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

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
