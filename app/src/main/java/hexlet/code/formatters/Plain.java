package hexlet.code.formatters;

import hexlet.code.ComparableLine;
import hexlet.code.Formatter;

import java.util.ArrayList;

public class Plain extends Formatter {
    @Override
    public String formatDiff(ArrayList<ComparableLine> lines) {
        return "";
    }
}
