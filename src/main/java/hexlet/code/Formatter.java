package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, List<Object>>> diffList, String format) throws Exception {

        return switch (format) {
            case "stylish" -> Stylish.stylishFormat(diffList);
            case "plain" -> Plain.plainFormat(diffList);
            case "json" -> Json.jsonFormat(diffList);
            default -> throw new RuntimeException("incorrect format: " + format);
        };
    }

}
