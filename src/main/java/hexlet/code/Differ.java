package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = Parser.parser(filePath1);
        Map<String, Object> map2 = Parser.parser(filePath2);

        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (String key : keySet) {
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else {
                if (map1.get(key).equals(map2.get(key))) {
                    result.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
                }
            }
        }
        result.append("}");

        return result.toString();
    }
}
