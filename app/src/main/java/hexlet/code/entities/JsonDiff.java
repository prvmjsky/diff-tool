package hexlet.code.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class JsonDiff {
    private HashMap<String, Object> same = new HashMap<>();
    private HashMap<String, Object> added = new HashMap<>();
    private HashMap<String, Object> removed = new HashMap<>();

    private HashMap<String, HashMap<String, Object>> updated = new HashMap<>();
    private HashMap<String, Object> before = new HashMap<>();
    private HashMap<String, Object> after = new HashMap<>();
}
