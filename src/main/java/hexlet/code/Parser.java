package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String stringFile, String ext) throws JsonProcessingException {

        return switch (ext) {
            case "json" -> new ObjectMapper().readValue(stringFile, new TypeReference<>() { });
            case "yml" -> new ObjectMapper(new YAMLFactory()).readValue(stringFile, new TypeReference<>() { });
            default -> throw new RuntimeException("incorrect file extension: " + ext);
        };

    }
}
