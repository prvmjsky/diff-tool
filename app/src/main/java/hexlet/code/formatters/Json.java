package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;

public class Json extends Formatter {
    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) {
        return "0";
    }
}
