package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ComparableLine implements Comparable<ComparableLine> {
    private String status;
    private String key;
    private String value;

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
