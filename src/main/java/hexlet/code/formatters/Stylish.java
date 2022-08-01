package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String format(List<Map<String, List<Object>>> diffList) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, List<Object>> map : diffList) {
            for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
                switch (entry.getKey()) {
                    case "added" ->
                            result.append("  + ").append(entry.getValue().get(0)).append(": ")
                                    .append(entry.getValue().get(1));
                    case "removed" ->
                            result.append("  - ").append(entry.getValue().get(0)).append(": ")
                                    .append(entry.getValue().get(1));
                    case "was updated" -> {
                        result.append("  - ").append(entry.getValue().get(0)).append(": ")
                                .append(entry.getValue().get(2)).append("\n");
                        result.append("  + ").append(entry.getValue().get(0)).append(": ")
                                .append(entry.getValue().get(1));
                    }
                    case "unchanged" ->
                            result.append("    ").append(entry.getValue().get(0)).append(": ")
                                    .append(entry.getValue().get(1));
                    default -> { }
                }
                result.append("\n");
            }
        }
        result.append("}");

        return result.toString();
    }

}
