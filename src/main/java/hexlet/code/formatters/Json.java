package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {

    public static String jsonFormat(List<Map<String, List<Object>>> diffList) throws Exception {
        Map<String, Object> diffMap = new LinkedHashMap<>();

        for (Map<String, List<Object>> map : diffList) {
            for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
                if (entry.getKey().equals("added")
                        || entry.getKey().equals("was updated")
                        || entry.getKey().equals("unchanged")) {
                    diffMap.put(entry.getValue().get(0).toString(), entry.getValue().get(1));
                }
            }
        }

        return new ObjectMapper().writeValueAsString(diffMap);
    }

}
