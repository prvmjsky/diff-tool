package hexlet.code.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public class JsonDiff {
    private LinkedHashMap<String, Object> same = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> added = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> removed = new LinkedHashMap<>();
    private LinkedHashMap<String, LinkedHashMap<String, Object>> updated = new LinkedHashMap<>();
}
