package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plainFormat(Map<String, List<Object>> diffList) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, List<Object>> map : diffList.entrySet()) {
            if (map.getValue().get(0).equals("added")) {
                result.append("Property '").append(map.getKey()).append("' was added with value: ")
                        .append(printValue(map.getValue().get(1))).append("\n");
            } else if (map.getValue().get(0).equals("removed")) {
                result.append("Property '").append(map.getKey()).append("' was removed").append("\n");
            } else if (map.getValue().get(0).equals("was updated")) {
                result.append("Property '").append(map.getKey()).append("' was updated. From ")
                        .append(printValue(map.getValue().get(1))).append(" to ")
                        .append(printValue(map.getValue().get(2))).append("\n");
            }
        }

        return result.toString().trim();
    }

    public static Object printValue(Object obj) {

        if (obj == null) {
            return "null";
        } else if (obj instanceof ArrayList || obj instanceof LinkedHashMap) {
            return new String("[complex value]");
        } else if (obj instanceof Integer || obj instanceof Boolean) {
            return obj;
        } else {
            return new String("'" + obj + "'");
        }
    }

}
