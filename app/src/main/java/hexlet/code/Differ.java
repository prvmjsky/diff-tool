package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = new HashMap<>(Parser.toMap(filepath1));
        var map2 = new HashMap<>(Parser.toMap(filepath2));
        var lines = new ArrayList<ComparableLine>();

        if (map1.equals(map2)) {
            map1.forEach((key, value) -> {
                lines.add(new ComparableLine("same", key, value));
            });

        } else {
            var keysUnion = new HashSet<>(map1.keySet());
            keysUnion.addAll(map2.keySet());

            keysUnion.forEach(key -> {
                var value1 = map1.get(key);
                var value2 = map2.get(key);

                if (map1.containsKey(key) && map2.containsKey(key)) {

                    if (Objects.equals(value1, value2)) {
                        lines.add(new ComparableLine("same", key, value1));
                    } else {
                        lines.add(new ComparableLine("updated", key, value2, value1));
                    }

                } else if (map1.containsKey(key)) {
                    lines.add(new ComparableLine("removed", key, value1));

                } else {
                    lines.add(new ComparableLine("added", key, value2));
                }
            });
        }

        var formatter = Formatter.getFormatter(format);
        return formatter.formatDiff(lines);
    }
}
