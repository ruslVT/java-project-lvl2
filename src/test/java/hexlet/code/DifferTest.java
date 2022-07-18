package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class DifferTest {

    @Test
    public void testGenerate() throws Exception {
        String jsonFile1 = "./src/test/resources/file1.json";
        String jsonFile2 = "./src/test/resources/file2.json";
        String actual1 = Differ.generate(jsonFile1, jsonFile2);
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

        String ymlFile1 = "./src/test/resources/file1.yml";
        String ymlFile2 = "./src/test/resources/file2.yml";
        String actual2 = Differ.generate(ymlFile1, ymlFile2);
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
    }

}
