package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {

    public static String jsonFormat(Map<String, List<Object>> diffList) throws Exception {
        Map<String, Object> diffMap = new LinkedHashMap<>();

        for (Map.Entry<String, List<Object>> map : diffList.entrySet()) {
            if (map.getValue().get(0).equals("added")
                    || map.getValue().get(0).equals("was updated")
                    || map.getValue().get(0).equals("unchanged")) {
                diffMap.put(map.getKey(), map.getValue().get(1));
            }
        }

        return new ObjectMapper().writeValueAsString(diffMap);
    }

}
