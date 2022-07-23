package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylishFormat(Map<String, List<Object>> diffList) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, List<Object>> entry : diffList.entrySet()) {
            if (entry.getValue().get(0).equals("added")) {
                result.append("  + ").append(entry.getKey()).append(": ").append(entry.getValue().get(1));
            } else if (entry.getValue().get(0).equals("removed")) {
                result.append("  - ").append(entry.getKey()).append(": ").append(entry.getValue().get(1));
            } else if (entry.getValue().get(0).equals("was updated")) {
                result.append("  - ").append(entry.getKey()).append(": ").append(entry.getValue().get(1));
                result.append("\n");
                result.append("  + ").append(entry.getKey()).append(": ").append(entry.getValue().get(2));
            } else if (entry.getValue().get(0).equals(" ")) {
                result.append("    ").append(entry.getKey()).append(": ").append(entry.getValue().get(1));
            }
            result.append("\n");
        }
        result.append("}");

        return result.toString();
    }

}
