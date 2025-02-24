package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Stylish extends Formatter {
    private String getPrefix(ComparableLine line) {
        return switch (line.getStatus()) {
            case "removed" -> "- ";
            case "added" -> "+ ";
            default -> "  ";
        };
    }

    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) {
        var diff = new ArrayList<ComparableLine>();
        lines.forEach(line -> {
            if (line.getStatus().equals("updated")) {
                diff.addAll(line.getSeparatedUpdated());
            } else {
                diff.add(line);
            }
        });

        return diff.stream()
                .sorted(Comparator.comparing(ComparableLine::getKey))
                .sorted(ComparableLine::compareTo)
                .map(d -> getPrefix(d) + d.getKey() + ": " + d.getValue())
                .collect(Collectors.joining("\n" + "  ",
                        "{\n" + "  ", "\n}"));
    }
}
