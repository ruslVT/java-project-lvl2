package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {

    public static String format(List<Map<String, List<Object>>> diffList) throws Exception {

        return new ObjectMapper().writeValueAsString(diffList
                .stream()
                .filter(map -> !map.containsKey("removed"))
                .flatMap(map -> map.values().stream())
                .collect(LinkedHashMap::new, (m, k) -> m.put(k.get(0), k.get(1)), LinkedHashMap::putAll));
    }

}
