package hexlet.code;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Differ {
    public static String sortResult(HashMap<String, Object> map) {
        return map.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .sorted(Comparator.comparing(str -> str.substring(2)))
                .collect(Collectors.joining("\n",
                        "{\n",  "\n}"));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        var map1 = new HashMap<>(Parser.parseJsonToMap(filepath1));
        var map2 = new HashMap<>(Parser.parseJsonToMap(filepath2));

        if (map1.equals(map2)) {
            return sortResult(map1);
        }

        var diffMap = new HashMap<String, Object>();

        map1.forEach((key, value1) -> {
            if (map2.containsKey(key)) {
                var value2 = map2.get(key);

                if (value1.equals(value2)) {
                    diffMap.put("  " + key, value1);
                } else {
                    diffMap.put("- " + key, value1);
                    diffMap.put("+ " + key, value2);
                }
            } else {
                diffMap.put("- " + key, value1);
            }
        });

        map2.forEach((key, value2) -> {
            if (!map1.containsKey(key)) {
                diffMap.put("+ " + key, value2);
            }
        });

        return sortResult(diffMap);
    }
}
