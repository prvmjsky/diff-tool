package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class ComparableLine implements Comparable<ComparableLine> {
    private String status;
    private String key;
    private String value;

    private String getPrefix() {
        return switch (this.status) {
            case "removed" -> "- ";
            case "added" -> "+ ";
            default -> "  ";
        };
    }

    public static String sortResult(ArrayList<ComparableLine> lines, String format) throws IOException {
        if (format.equals("stylish")) {
            return lines.stream()
                    .sorted(Comparator.comparing(ComparableLine::getKey))
                    .sorted(ComparableLine::compareTo)
                    .map(d -> d.getPrefix() + d.getKey() + ": " + d.getValue())
                    .collect(Collectors.joining("\n" + "  ",
                            "{\n" + "  ", "\n}"));
        } else {
            throw new IOException("wrong style format");
        }
    }

    @Override
    public int compareTo(ComparableLine line) {

        if (this.key.equals(line.key)) {
            if (this.status.equals("removed") && line.status.equals("added")) {
                return -1;
            }

            if (line.status.equals("removed") && this.status.equals("added")) {
                return 1;
            }
        }

        return 0;
    }
}
