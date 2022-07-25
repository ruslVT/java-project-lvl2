package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylishFormat(List<Map<String, List<Object>>> diffList) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, List<Object>> map : diffList) {
            for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
                if (entry.getKey().equals("added")) {
                    result.append("  + ").append(entry.getValue().get(0)).append(": ").append(entry.getValue().get(1));
                } else if (entry.getKey().equals("removed")) {
                    result.append("  - ").append(entry.getValue().get(0)).append(": ").append(entry.getValue().get(1));
                } else if (entry.getKey().equals("was updated")) {
                    result.append("  - ").append(entry.getValue().get(0)).append(": ").append(entry.getValue().get(2));
                    result.append("\n");
                    result.append("  + ").append(entry.getValue().get(0)).append(": ").append(entry.getValue().get(1));
                } else if (entry.getKey().equals("unchanged")) {
                    result.append("    ").append(entry.getValue().get(0)).append(": ").append(entry.getValue().get(1));
                }
                result.append("\n");
            }
        }
        result.append("}");

        return result.toString();
    }

}
