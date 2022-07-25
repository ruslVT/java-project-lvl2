package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plainFormat(List<Map<String, List<Object>>> diffList) {
        StringBuilder result = new StringBuilder();

        for (Map<String, List<Object>> map : diffList) {
            for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
                if (entry.getKey().equals("added")) {
                    result.append("Property '").append(entry.getValue().get(0)).append("' was added with value: ")
                            .append(printValue(entry.getValue().get(1))).append("\n");
                } else if (entry.getKey().equals("removed")) {
                    result.append("Property '").append(entry.getValue().get(0)).append("' was removed").append("\n");
                } else if (entry.getKey().equals("was updated")) {
                    result.append("Property '").append(entry.getValue().get(0)).append("' was updated. From ")
                            .append(printValue(entry.getValue().get(2))).append(" to ")
                            .append(printValue(entry.getValue().get(1))).append("\n");
                }
            }
        }

        return result.toString().trim();
    }

    public static Object printValue(Object obj) {

        if (obj == null || obj instanceof Integer || obj instanceof Boolean) {
            return obj;
        } else if (obj instanceof ArrayList || obj instanceof LinkedHashMap) {
            return new String("[complex value]");
        } else {
            return new String("'" + obj + "'");
        }
    }

}
