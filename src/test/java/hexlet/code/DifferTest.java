package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class DifferTest {

    private String pathJson1 = "./src/test/resources/file1.json";
    private String pathJson2 = "./src/test/resources/file2.json";
    private String pathYml1 = "./src/test/resources/file1.yml";
    private String pathYml2 = "./src/test/resources/file2.yml";
    private String emptyFile = "./src/test/resources/emptyFile.json";

    private String expectedStylish = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
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

    private String expectedPlain = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

    private String expectedJson = "{\"chars1\":[\"a\",\"b\",\"c\"],\"chars2\":false,\"checked\":true,"
            + "\"default\":[\"value1\",\"value2\"],\"id\":null,\"key2\":\"value2\",\"numbers1\":[1,2,3,4],"
            + "\"numbers2\":[22,33,44,55],\"numbers4\":[4,5,6],\"obj1\":{\"nestedKey\":\"value\","
            + "\"isNested\":true},\"setting1\":\"Another value\",\"setting2\":300,\"setting3\":\"none\"}";

    @Test
    public void testGenerate() throws Exception {

        // Stylish format
        String actual1 = Differ.generate(pathJson1, pathJson2, "stylish");
        String expected1 = expectedStylish;
        assertThat(actual1).isEqualTo(expected1);

        String actual2 = Differ.generate(pathYml1, pathYml2, "stylish");
        String expected2 = expectedStylish;
        assertThat(actual2).isEqualTo(expected2);

        String actual3 = Differ.generate(pathJson1, pathYml2, "stylish");
        String expected3 = expectedStylish;
        assertThat(actual3).isEqualTo(expected3);

        // Plain format
        String actual4 = Differ.generate(pathJson1, pathJson2, "plain");
        String expected4 = expectedPlain;
        assertThat(actual4).isEqualTo(expected4);

        String actual5 = Differ.generate(pathYml1, pathYml2, "plain");
        String expected5 = expectedPlain;
        assertThat(actual5).isEqualTo(expected5);

        String actual6 = Differ.generate(pathJson1, pathYml2, "plain");
        String expected6 = expectedPlain;
        assertThat(actual6).isEqualTo(expected6);

        // Json format
        String actual7 = Differ.generate(pathJson1, pathJson2, "json");
        String expected7 = expectedJson;
        assertThat(actual7).isEqualTo(expected7);

        String actual8 = Differ.generate(pathYml1, pathYml2, "json");
        String expected8 = expectedJson;
        assertThat(actual8).isEqualTo(expected8);

        String actual9 = Differ.generate(pathJson1, pathYml2, "json");
        String expected9 = expectedJson;
        assertThat(actual9).isEqualTo(expected9);

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
        String actual10 = Differ.generate(pathJson1, pathJson2, "other");
        String expected10 = "Incorrect format";
        assertThat(actual10).isEqualTo(expected10);

        // missing file
        assertThrows(FileNotFoundException.class, () -> {
            String str4 = Differ.generate(pathJson1, "./src/test/resources/file.json", "stylish");
        });
    }

}
