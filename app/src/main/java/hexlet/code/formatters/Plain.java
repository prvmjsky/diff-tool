package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain extends Formatter {
    private static String toPlainStringElement(Object value) {
        if (value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }

    private static String toPlainString(ComparableLine line) {
        var key = toPlainStringElement(line.getKey());
        var value = toPlainStringElement(line.getValue());
        var oldValue = toPlainStringElement(line.getOldValue());

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
                .filter(line -> !line.getStatus().equals("same"))
                .sorted(Comparator.comparing(ComparableLine::getKey))
                .sorted(ComparableLine::compareTo)
                .map(Plain::toPlainString)
                .collect(Collectors.joining("\n"));
    }
}
