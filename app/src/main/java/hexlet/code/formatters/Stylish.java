package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class Stylish extends Formatter {
    private String getPrefix(ComparableLine line) {
        return switch (line.getStatus()) {
            case "removed" -> "- ";
            case "added" -> "+ ";
            case "same" -> "  ";
            default -> throw new IllegalArgumentException("Unknown status of line");
        };
    }

    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) {
        var diff = new ArrayList<ComparableLine>();

        lines.forEach(line -> {
            if (line.getStatus().equals("updated")) {
                diff.addAll(line.getBeforeAndAfter());
            } else {
                diff.add(line);
            }
        });

        return diff.stream()
                .sorted(ComparableLine::compareTo)
                .map(line -> getPrefix(line) + line.getKey() + ": " + line.getValue())
                .collect(Collectors.joining("\n" + "  ",
                        "{\n" + "  ", "\n}"));
    }
}
