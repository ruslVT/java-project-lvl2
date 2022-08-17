package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DifferTest {

    private final String pathJson1 = "./src/test/resources/file1.json";
    private final String pathJson2 = "./src/test/resources/file2.json";
    private final String pathYml1 = "./src/test/resources/file1.yml";
    private final String pathYml2 = "./src/test/resources/file2.yml";
    private final String emptyFile = "./src/test/resources/emptyFile.json";
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void beforeAll() throws IOException {
        expectedStylish = Files.readString(Paths.get("./src/test/resources/expectedStylish.txt"))
                .trim();

        expectedPlain = Files.readString(Paths.get("./src/test/resources/expectedPlain.txt"))
                .trim();

        expectedJson = Files.readString(Paths.get("./src/test/resources/expectedJson.txt"))
                .trim();
    }

    public DifferTest() throws IOException {
    }

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
        assertThrows(NoSuchFileException.class, () -> {
            String str5 = Differ.generate(pathJson1, "./src/test/resources/file.json", "stylish");
        });

        assertThrows(RuntimeException.class, () -> {
            String str6 = Differ.generate(pathJson1, "./src/test/resources/file.wrong", "stylish");
        });

        assertThrows(JsonParseException.class, () -> {
            String str7 = Differ.generate(pathJson1, "./src/test/resources/fake.json", "stylish");
        });

    }

}
