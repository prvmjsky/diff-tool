package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Plain extends Formatter {
    private static String hideValueIfComplex(String value) {
        if ((value.startsWith("{") && value.endsWith("}")) || (value.startsWith("[") && value.endsWith("]"))) {
            return "[]"
        }
    }

    private static String lineToPlainString(ComparableLine line) {
        var key = hideValueIfComplex(line.getKey());
        var value = hideValueIfComplex(line.getValue());
        var oldValue = hideValueIfComplex(line.getOldValue());

        return switch (line.getStatus()) {
            case "removed" -> "Property '" + key + "' was removed";
            case "added" -> "Property '" + key + "' was added with value: " + value;
            case "updated" -> "Property '" + key + "' was updated. From " + oldValue + " to " + value;
            default -> "nothing";
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
