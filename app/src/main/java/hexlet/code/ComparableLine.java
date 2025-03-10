package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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

    public static ArrayList<ComparableLine> compareMaps(HashMap<String, Object> map1, HashMap<String, Object> map2) {
        var lines = new ArrayList<ComparableLine>();

        if (map1.equals(map2)) {
            map1.forEach((key, value) -> {
                lines.add(new ComparableLine("same", key, value));
            });

        } else {
            var keysUnion = new HashSet<>(map1.keySet());
            keysUnion.addAll(map2.keySet());

            keysUnion.forEach(key -> {
                var value1 = map1.get(key);
                var value2 = map2.get(key);

                if (map1.containsKey(key) && map2.containsKey(key)) {

                    if (Objects.equals(value1, value2)) {
                        lines.add(new ComparableLine("same", key, value1));
                    } else {
                        lines.add(new ComparableLine("updated", key, value2, value1));
                    }

                } else if (map1.containsKey(key)) {
                    lines.add(new ComparableLine("removed", key, value1));

                } else {
                    lines.add(new ComparableLine("added", key, value2));
                }
            });
        }

        return lines;
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
