package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatStyle) throws Exception {
        Map<String, Object> map1 = Parser.parser(filePath1);
        Map<String, Object> map2 = Parser.parser(filePath2);

        List<Map<String, List<Object>>> diffList = new ArrayList<>(DiffBuilder.buildDiff(map1, map2));

        return Formatter.format(diffList, formatStyle);
    }
}
