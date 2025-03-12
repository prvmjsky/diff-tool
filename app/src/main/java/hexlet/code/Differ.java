package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        var data = readFile(filepath);
        var fileType = getFileType(filepath);
        return new HashMap<>(Parser.toMap(data, fileType));
    }

    public static String readFile(String path) throws IOException {
        var normalizedPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(normalizedPath);
    }

    public static String getFileType(String path) throws IOException {
        if (path.endsWith(".json")) {
            return "json";
        } else if (path.endsWith(".yml") || path.endsWith(".yaml")) {
            return "yaml";
        } else {
            throw new IOException("wrong file extension");
        }
    }
}
