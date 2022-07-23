package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, List<Object>> diffList, String format) {
        String result = "";

        if (format.equals("stylish")) {
            result = Stylish.stylishFormat(diffList);
        } else if (format.equals("plain")) {
            result = Plain.plainFormat(diffList);
        } else {
            result = "Incorrect format";
        }

        return result;
    }

}
