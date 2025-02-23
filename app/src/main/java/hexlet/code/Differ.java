package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
                            "{\n" + "  ", "\n}"));
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

        return sortResult(lines, format);
    }
}
