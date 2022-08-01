package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, List<Object>>> diffList, String format) throws Exception {

        return switch (format) {
            case "stylish" -> Stylish.format(diffList);
            case "plain" -> Plain.format(diffList);
            case "json" -> Json.format(diffList);
            default -> throw new RuntimeException("incorrect format: " + format);
        };
    }

}
