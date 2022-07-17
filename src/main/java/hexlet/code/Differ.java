package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(new File(filePath1), new TypeReference<>() { });
        Map<String, Object> map2 = mapper.readValue(new File(filePath2), new TypeReference<>() { });
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        return diff(map1, map2, keySet);
    }

    public static String diff(Map<String, Object> map1, Map<String, Object> map2, Set<String> keySet) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key : keySet) {
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
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
