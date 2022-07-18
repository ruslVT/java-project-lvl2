package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;

public class DifferTest {

    @Test
    public void testGenerate() throws Exception {

        String actual1 = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json");
        String expected1 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(actual1).isEqualTo(expected1);

        String actual2 = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml");
        String expected2 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(actual2).isEqualTo(expected2);

        String actual3 = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.yml");
        String expected3 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(actual3).isEqualTo(expected3);

        assertThrows(MismatchedInputException.class, () -> {
            String str = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/emptyFile.json");
        });
    }

}
