package hexlet.code;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatStyle) throws Exception {
        Map<String, Object> map1 = Parser.parser(filePath1);
        Map<String, Object> map2 = Parser.parser(filePath2);
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        Map<String, List<Object>> diffList = new LinkedHashMap<>();

        for (String key : keySet) {
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                diffList.put(key, Arrays.asList("added", map2.get(key)));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                diffList.put(key, Arrays.asList("removed", map1.get(key)));
            } else if ((map1.get(key) == null && map2.get(key) == null)) {
                diffList.put(key, Arrays.asList(" ", map1.get(key)));
            } else if ((map1.get(key) == null || map2.get(key) == null)) {
                diffList.put(key, Arrays.asList("was updated", map2.get(key), map1.get(key)));
            } else if (map1.get(key).equals(map2.get(key))) {
                diffList.put(key, Arrays.asList(" ", map1.get(key)));
            } else {
                diffList.put(key, Arrays.asList("was updated", map2.get(key), map1.get(key)));
            }
        }

        return Formatter.format(diffList, formatStyle);
    }
}
