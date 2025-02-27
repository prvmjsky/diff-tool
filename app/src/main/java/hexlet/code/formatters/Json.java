package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.ComparableLine;
import hexlet.code.Formatter;
import hexlet.code.Parser;
import hexlet.code.entities.JsonDiff;

import java.util.ArrayList;

public class Json extends Formatter {
    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) throws JsonProcessingException {
        var diff = new JsonDiff();

        lines.forEach(line -> {
            var key = line.getKey();
            var value = line.getValue();

            switch (line.getStatus()) {
                case "same" -> diff.getSame().put(key, value);
                case "added" -> diff.getAdded().put(key, value);
                case "removed" -> diff.getRemoved().put(key, value);
                case "updated" -> {
                    diff.getBefore().put(key, line.getOldValue());
                    diff.getAfter().put(key, value);
                }
                default -> throw new IllegalArgumentException("Unknown status of line");
            }
        });

        diff.getUpdated().put("before", diff.getBefore());
        diff.getUpdated().put("after", diff.getAfter());

        return Parser.toString(diff);
    }
}
