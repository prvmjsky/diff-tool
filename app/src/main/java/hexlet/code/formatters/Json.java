package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.ComparableLine;
import hexlet.code.Formatter;
import hexlet.code.Parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Json extends Formatter {
    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) throws JsonProcessingException {
        var diff = new LinkedHashMap<String, LinkedHashMap<String, Object>>();
        var sortedLines = lines.stream().sorted(ComparableLine::compareTo);

        sortedLines.forEach(line -> {
            var key = line.getKey();
            var value = line.getValue();
            var oldValue = line.getOldValue();
            var info = new LinkedHashMap<String, Object>();

            switch (line.getStatus()) {
                case "same" -> {
                    info.put("status", "same");
                    info.put("value", value);
                }
                case "added" -> {
                    info.put("status", "added");
                    info.put("value", value);
                }
                case "removed" -> {
                    info.put("status", "removed");
                    info.put("value", value);
                }
                case "updated" -> {
                    info.put("status", "updated");
                    info.put("oldValue", oldValue);
                    info.put("value", value);
                }
                default -> throw new IllegalArgumentException("Unknown status of line");
            }

            diff.put(key, info);
        });

        return Parser.toString(diff);
    }
}
