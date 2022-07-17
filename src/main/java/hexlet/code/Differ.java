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

        StringBuilder result = new StringBuilder("{\n");
        for (String key : keySet) {
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append(buildString("  + ", key, map2.get(key).toString()));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append(buildString("  - ", key, map1.get(key).toString()));
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    result.append(buildString("    ", key, map1.get(key).toString()));
                } else {
                    result.append(buildString("  - ", key, map1.get(key).toString()));
                    result.append(buildString("  + ", key, map2.get(key).toString()));
                }
            }
        }
        result.append("}");

        return result.toString().toString();
    }

    public static String buildString(String sign, String str1, String str2) {
        return sign + str1 + ": " + str2 + "\n";
    }

}
