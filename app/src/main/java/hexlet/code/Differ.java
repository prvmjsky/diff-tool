package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Differ {
    private static String getPrefix(ComparableLine line) {
        var status = line.getStatus();

        return switch (status) {
            case "removed" -> "- ";
            case "added" -> "+ ";
            default -> "  ";
        };
    }

    private static String sortResult(ArrayList<ComparableLine> lines, String format) throws IOException {
        if (format.equals("stylish")) {
            return lines.stream()
                    .sorted(Comparator.comparing(ComparableLine::getKey))
                    .sorted(ComparableLine::compareTo)
                    .map(d -> getPrefix(d) + d.getKey() + ": " + d.getValue())
                    .collect(Collectors.joining("\n" + "  ",
                            "{\n" + "  ",  "\n}"));
        } else {
            throw new IOException("wrong style format");
        }
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = new HashMap<>(Parser.parseToMap(filepath1));
        var map2 = new HashMap<>(Parser.parseToMap(filepath2));
        var lines = new ArrayList<ComparableLine>();

        if (map1.equals(map2)) {
            map1.forEach((key, value) -> {
                lines.add(new ComparableLine("same", key, String.valueOf(value)));
            });

        } else {
            var keySet1 = map1.keySet();
            var keySet2 = map2.keySet();
            var removed = new HashSet<>(keySet1);
            var added = new HashSet<>(keySet2);
            var common = new HashSet<>(keySet1);

            removed.removeAll(keySet2);
            added.removeAll(keySet1);
            common.retainAll(keySet2);

            removed.forEach(key -> {
                var value = String.valueOf(map1.get(key));
                lines.add(new ComparableLine("removed", key, value));
            });


            added.forEach(key -> {
                var value = String.valueOf(map2.get(key));
                lines.add(new ComparableLine("added", key, value));
            });

            common.forEach(key -> {
                var value1 = String.valueOf(map1.get(key));
                var value2 = String.valueOf(map2.get(key));

                if (value1.equals(value2)) {
                    lines.add(new ComparableLine("same", key, value1));
                } else {
                    lines.add(new ComparableLine("removed", key, value1));
                    lines.add(new ComparableLine("added", key, value2));
                }
            });
        }

        return sortResult(lines, format);
    }
}
