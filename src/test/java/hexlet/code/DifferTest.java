package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class DifferTest {

    private final String pathJson1 = "./src/test/resources/file1.json";
    private final String pathJson2 = "./src/test/resources/file2.json";
    private final String pathYml1 = "./src/test/resources/file1.yml";
    private final String pathYml2 = "./src/test/resources/file2.yml";
    private final String emptyFile = "./src/test/resources/emptyFile.json";

    private final String expectedStylish = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "    id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";

    private final String expectedPlain = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

    private final String expectedJson = "{\"chars1\":[\"a\",\"b\",\"c\"],\"chars2\":false,\"checked\":true,"
            + "\"default\":[\"value1\",\"value2\"],\"id\":null,\"key2\":\"value2\",\"numbers1\":[1,2,3,4],"
            + "\"numbers2\":[22,33,44,55],\"numbers4\":[4,5,6],\"obj1\":{\"nestedKey\":\"value\","
            + "\"isNested\":true},\"setting1\":\"Another value\",\"setting2\":300,\"setting3\":\"none\"}";

    // Stylish format
    @Test
    public void stylishFormat() throws Exception {
        // without format argument
        String actual = Differ.generate(pathJson1, pathJson2);
        assertThat(actual).isEqualTo(expectedStylish);

        String actual1 = Differ.generate(pathJson1, pathJson2, "stylish");
        assertThat(actual1).isEqualTo(expectedStylish);

        String actual2 = Differ.generate(pathYml1, pathYml2, "stylish");
        assertThat(actual2).isEqualTo(expectedStylish);

        String actual3 = Differ.generate(pathJson1, pathYml2, "stylish");
        assertThat(actual3).isEqualTo(expectedStylish);
    }

    // Plain format
    @Test
    public void plainFormat() throws Exception {
        // Plain format
        String actual1 = Differ.generate(pathJson1, pathJson2, "plain");
        assertThat(actual1).isEqualTo(expectedPlain);

        String actual2 = Differ.generate(pathYml1, pathYml2, "plain");
        assertThat(actual2).isEqualTo(expectedPlain);

        String actual3 = Differ.generate(pathJson1, pathYml2, "plain");
        assertThat(actual3).isEqualTo(expectedPlain);
    }

    // Json format
    @Test
    public void jsonFormat() throws Exception {
        String actual1 = Differ.generate(pathJson1, pathJson2, "json");
        assertThat(actual1).isEqualTo(expectedJson);

        String actual2 = Differ.generate(pathYml1, pathYml2, "json");
        assertThat(actual2).isEqualTo(expectedJson);

        String actual3 = Differ.generate(pathJson1, pathYml2, "json");
        assertThat(actual3).isEqualTo(expectedJson);
    }

    @Test
    public void except() {
        // assert with empty file
        assertThrows(MismatchedInputException.class, () -> {
            String str1 = Differ.generate(pathJson1, emptyFile, "stylish");
        });

        assertThrows(MismatchedInputException.class, () -> {
            String str2 = Differ.generate(pathJson1, emptyFile, "plain");
        });

        assertThrows(MismatchedInputException.class, () -> {
            String str3 = Differ.generate(pathJson1, emptyFile, "json");
        });

        // Incorrect format
        assertThrows(RuntimeException.class, () -> {
            String str4 = Differ.generate(pathJson1, pathJson2, "other");
        });

        // missing file
        assertThrows(FileNotFoundException.class, () -> {
            String str5 = Differ.generate(pathJson1, "./src/test/resources/file.json", "stylish");
        });

        assertThrows(RuntimeException.class, () -> {
            String str5 = Differ.generate(pathJson1, "./src/test/resources/file2.jso", "stylish");
        });
    }

}
