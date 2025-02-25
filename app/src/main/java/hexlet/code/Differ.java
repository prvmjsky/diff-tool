package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = new HashMap<>(Parser.parseToMap(filepath1));
        var map2 = new HashMap<>(Parser.parseToMap(filepath2));
        var lines = new ArrayList<ComparableLine>();

        if (map1.equals(map2)) {
            map1.forEach((key, value) -> {
                lines.add(new ComparableLine("same", key, value));
            });

        } else {
            map1.forEach((key, value) -> {

                if (map2.containsKey(key)) {
                    var value2 = map2.get(key);
                    map2.remove(key);

                    if (Objects.equals(value, value2)) {
                        lines.add(new ComparableLine("same", key, value));
                    } else {
                        lines.add(new ComparableLine("updated", key, value2, value));
                    }

                } else {
                    lines.add(new ComparableLine("removed", key, value));
                }
            });

            map2.forEach((key, value) -> {
                lines.add(new ComparableLine("added", key, value));
            });
        }

        var formatter = Formatter.getFormatter(format);
        return formatter.formatDiff(lines);
    }
}
