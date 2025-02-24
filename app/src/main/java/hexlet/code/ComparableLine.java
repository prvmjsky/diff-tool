package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ComparableLine implements Comparable<ComparableLine> {
    private String status;
    private String key;
    private String value;
    private String oldValue;

    public ComparableLine(String status, String key, String value) {
        this.status = status;
        this.key = key;
        this.value = value;
    }

    public List<ComparableLine> getSeparatedUpdated() {
        var before = new ComparableLine("removed", this.key, this.oldValue);
        var after = new ComparableLine("added", this.key, this.value);
        return List.of(before, after);
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
