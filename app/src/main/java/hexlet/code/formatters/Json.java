package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.ComparableLine;
import hexlet.code.Formatter;
import hexlet.code.Parser;
import hexlet.code.entities.JsonDiff;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Json extends Formatter {
    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) throws JsonProcessingException {
        var diff = new JsonDiff();
        var before = new LinkedHashMap<String, Object>();
        var after = new LinkedHashMap<String, Object>();

        var sortedLines = lines.stream().sorted(ComparableLine::compareTo);
        sortedLines.forEach(line -> {
            var key = line.getKey();
            var value = line.getValue();

            switch (line.getStatus()) {
                case "same" -> diff.getSame().put(key, value);
                case "added" -> diff.getAdded().put(key, value);
                case "removed" -> diff.getRemoved().put(key, value);
                case "updated" -> {
                    before.put(key, line.getOldValue());
                    after.put(key, value);
                }
                default -> throw new IllegalArgumentException("Unknown status of line");
            }
        });

        diff.getUpdated().put("before", before);
        diff.getUpdated().put("after", after);

        return Parser.toString(diff);
    }
}
