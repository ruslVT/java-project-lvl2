package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;

public class DifferTest {

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

    @Test
    public void testGenerate() throws Exception {

        // Stylish
        String actual1 = Differ.generate("./src/test/resources/file3.json",
                "./src/test/resources/file4.json", "stylish");
        String expected1 = expectedStylish;
        assertThat(actual1).isEqualTo(expected1);

        String actual2 = Differ.generate("./src/test/resources/file3.yml",
                "./src/test/resources/file4.yml", "stylish");
        String expected2 = expectedStylish;
        assertThat(actual2).isEqualTo(expected2);

        String actual3 = Differ.generate("./src/test/resources/file3.json",
                "./src/test/resources/file4.yml", "stylish");
        String expected3 = expectedStylish;
        assertThat(actual3).isEqualTo(expected3);

        // Plain
        String actual4 = Differ.generate("./src/test/resources/file3.json",
                "./src/test/resources/file4.json", "plain");
        String expected4 = expectedPlain;
        assertThat(actual4).isEqualTo(expected4);

        String actual5 = Differ.generate("./src/test/resources/file3.yml",
                "./src/test/resources/file4.yml", "plain");
        String expected5 = expectedPlain;
        assertThat(actual5).isEqualTo(expected5);

        String actual6 = Differ.generate("./src/test/resources/file3.json",
                "./src/test/resources/file4.yml", "plain");
        String expected6 = expectedPlain;
        assertThat(actual6).isEqualTo(expected6);

        // assert with empty file
        assertThrows(MismatchedInputException.class, () -> {
            String str1 = Differ.generate("./src/test/resources/file3.json",
                    "./src/test/resources/emptyFile.json", "stylish");
        });

        assertThrows(MismatchedInputException.class, () -> {
            String str2 = Differ.generate("./src/test/resources/file3.json",
                    "./src/test/resources/emptyFile.json", "plain");
        });

        // Incorrect format
        String actual7 = Differ.generate("./src/test/resources/file3.json",
                "./src/test/resources/file4.json", "other");
        String expected7 = "Incorrect format";
        assertThat(actual7).isEqualTo(expected7);
    }

}
