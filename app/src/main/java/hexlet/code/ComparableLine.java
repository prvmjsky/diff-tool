package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public final class ComparableLine implements Comparable<ComparableLine> {
    private String status;
    private String key;
    private Object value;
    private Object oldValue;

    // constructor for non-updated line
    public ComparableLine(String status, String key, Object value) {
        this.status = status;
        this.key = key;
        this.value = value;
    }

    public List<ComparableLine> getBeforeAndAfter() {
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

        return this.key.compareTo(line.key);
    }
}
