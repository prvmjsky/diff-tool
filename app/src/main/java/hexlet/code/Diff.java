package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Diff implements Comparable<Diff> {
    private String status;
    private String key;
    private String value;

    @Override
    public int compareTo(Diff diff) {

        if (this.key.equals(diff.key)) {
            if (this.status.equals("before") && diff.status.equals("after")) {
                return 1;
            }

            if (diff.status.equals("before") && this.status.equals("after")) {
                return -1;
            }
        }

        return 0;
    }
}
