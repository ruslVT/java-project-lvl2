package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String path) throws Exception {
        String ext = FilenameUtils.getExtension(path);
        Map<String, Object> result = new HashMap<>();

        if (ext.equals("json")) {
            result = new ObjectMapper().readValue(new File(path), new TypeReference<>() { });
        } else if (ext.equals("yml")) {
            result = new ObjectMapper(new YAMLFactory()).readValue(new File(path), new TypeReference<>() { });
        }

        return result;
    }
}
