package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Plain extends Formatter {
    private static String formatValue(Object value) {
        if (value instanceof Collection) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }

    private static String lineToPlainString(ComparableLine line) {
        var key = formatValue(line.getKey());
        var value = formatValue(line.getValue());
        var oldValue = formatValue(line.getOldValue());

        return switch (line.getStatus()) {
            case "removed" -> "Property " + key + " was removed";
            case "added" -> "Property " + key + " was added with value: " + value;
            case "updated" -> "Property " + key + " was updated. From " + oldValue + " to " + value;
            default -> throw new IllegalArgumentException("Unknown status of line");
        };
    }

    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) {

        return lines.stream()
                .sorted(Comparator.comparing(ComparableLine::getKey))
                .sorted(ComparableLine::compareTo)
                .map(Plain::lineToPlainString)
                .collect(Collectors.joining("\n"));
    }
}
