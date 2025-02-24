package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = new HashMap<>(Parser.parseToMap(filepath1));
        var map2 = new HashMap<>(Parser.parseToMap(filepath2));
        var lines = new ArrayList<ComparableLine>();

        if (map1.equals(map2)) {
            map1.forEach((key, value) -> {
                lines.add(new ComparableLine("same", key, String.valueOf(value)));
            });

        } else {
            map1.forEach((key, value) -> {
                var value1 = String.valueOf(value);

                if (map2.containsKey(key)) {
                    var value2 = String.valueOf(map2.get(key));
                    map2.remove(key);

                    if (value2.equals(value1)) {
                        lines.add(new ComparableLine("same", key, value1));
                    } else {
                        lines.add(new ComparableLine("removed", key, value1));
                        lines.add(new ComparableLine("added", key, value2));
                    }

                } else {
                    lines.add(new ComparableLine("removed", key, value1));
                }
            });

            map2.forEach((key, value) -> {
                lines.add(new ComparableLine("added", key, String.valueOf(value)));
            });
        }

        var formatter = Formatter.getFormatter(format);
        return formatter.formatDiff(lines);
    }
}
