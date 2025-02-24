package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;

public class Plain extends Formatter {
    private static String lineToPlainString(ComparableLine line) {
        return switch (line.getStatus()) {
            case "removed" -> "Property '" + line.getKey() + "' was removed";
            case "added" -> "Property '" + line.getKey() + "' was added with value: " + line.getValue();
            case "updated" -> "Property '" + line.getKey() + "' was updated. From " + line.getOldValue() + " to " + line.getValue();
            default -> "nothing";
        };
    }

    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) {
        var diff = new ArrayList<String>();

        lines.forEach(line -> diff.add(lineToPlainString(line)));
        return null;
    }
}
