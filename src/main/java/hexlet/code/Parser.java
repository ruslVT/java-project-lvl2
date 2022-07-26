package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String path) throws Exception {
        String ext = FilenameUtils.getExtension(path);

        return switch (ext) {
            case "json" -> new ObjectMapper().readValue(new File(path), new TypeReference<>() { });
            case "yml" -> new ObjectMapper(new YAMLFactory()).readValue(new File(path), new TypeReference<>() { });
            default -> throw new RuntimeException("incorrect file extension: " + ext);
        };

    }
}
