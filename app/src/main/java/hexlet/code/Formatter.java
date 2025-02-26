package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Formatter {
    public static Formatter getFormatter(String format) throws IOException {
        return switch (format) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default -> throw new IOException("Unknown output format");
        };
    }

    public abstract String formatDiff(ArrayList<ComparableLine> lines);
}
