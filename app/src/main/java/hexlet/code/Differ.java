package hexlet.code;

import java.io.IOException;
import java.util.HashMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = getMap(filepath1);
        var map2 = getMap(filepath2);
        var lines = ComparableLine.compareMaps(map1, map2);

        var formatter = Formatter.getFormatter(format);
        return formatter.formatDiff(lines);
    }

    private static HashMap<String, Object> getMap(String filepath) throws IOException {
        var data = Parser.readFile(filepath);
        var fileType = Parser.getFileType(filepath);
        return new HashMap<>(Parser.toMap(data, fileType));
    }
}
