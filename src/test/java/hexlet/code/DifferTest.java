package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class DifferTest {

    @Test
    public void testGenerate() throws Exception {
        String path1 = "./src/test/resources/file1.json";
        String path2 = "./src/test/resources/file2.json";
        String actual = Differ.generate(path1, path2);
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
    }

}
