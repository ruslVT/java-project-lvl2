package hexlet.code;

import org.apache.commons.io.FilenameUtils;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatStyle) throws Exception {

        String stringFile1 = Files.readString(Path.of(filePath1), StandardCharsets.UTF_8);
        String stringFile2 = Files.readString(Path.of(filePath2), StandardCharsets.UTF_8);

        String ext1 = FilenameUtils.getExtension(filePath1);
        String ext2 = FilenameUtils.getExtension(filePath2);

        Map<String, Object> map1 = Parser.parser(stringFile1, ext1);
        Map<String, Object> map2 = Parser.parser(stringFile2, ext2);

        List<Map<String, List<Object>>> diffList = new ArrayList<>(DiffBuilder.buildDiff(map1, map2));

        return Formatter.format(diffList, formatStyle);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
