package hexlet.code.formatters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String format(List<Map<String, List<Object>>> diffList) throws IOException {
        StringBuilder result = new StringBuilder();

        for (Map<String, List<Object>> map : diffList) {
            for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
                switch (entry.getKey()) {
                    case "added" ->
                            result.append("Property '").append(entry.getValue().get(0))
                                    .append("' was added with value: ")
                                    .append(printValue(entry.getValue().get(1))).append("\n");
                    case "removed" ->
                            result.append("Property '").append(entry.getValue().get(0))
                                    .append("' was removed").append("\n");
                    case "was updated" ->
                            result.append("Property '").append(entry.getValue().get(0))
                                    .append("' was updated. From ")
                                    .append(printValue(entry.getValue().get(2))).append(" to ")
                                    .append(printValue(entry.getValue().get(1))).append("\n");
                    default -> { }
                }
            }
        }

        return result.toString().trim();
    }

    public static Object printValue(Object obj) {

        if (obj == null || obj instanceof Integer || obj instanceof Boolean) {
            return obj;
        } else if (obj instanceof ArrayList || obj instanceof LinkedHashMap) {
            return "[complex value]";
        } else {
            return "'" + obj + "'";
        }
    }

}
