package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Differ {
    private static String sortResult(ArrayList<Diff> diffs, String format) {

    }

//    public static String sortResult(HashMap<String, Object> map) {
//        return map.keySet().stream()
//                .sorted(Comparator.comparing(key -> key.substring(2)))
//                .sorted((key1, key2) -> {
//                    if (key1.substring(2).equals(key2.substring(2))) {
//                        if (key1.startsWith("-")) {
//                            return -1;
//                        } else {
//                            return 1;
//                        }
//                    } else {
//                        return 0;
//                    }
//                })
//                .map(key -> key + ": " + map.get(key))
//                .collect(Collectors.joining("\n" + "  ",
//                        "{\n" + "  ",  "\n}"));
//    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        var map1 = new HashMap<>(Parser.parseToMap(filepath1));
        var map2 = new HashMap<>(Parser.parseToMap(filepath2));
        var diffs = new ArrayList<Diff>();

        if (map1.equals(map2)) {
            map1.forEach((key, value) -> {
                diffs.add(new Diff("same", key, value.toString()));
            });

        } else {
            var keySet1 = map1.keySet();
            var keySet2 = map2.keySet();
            var removed = new HashSet<>(keySet1);
            var added = new HashSet<>(keySet2);
            var common = new HashSet<>(keySet1);

            removed.removeAll(keySet2);
            added.removeAll(keySet1);
            common.retainAll(keySet2);

            removed.forEach(key -> {
                var value = map1.get(key).toString();
                diffs.add(new Diff("removed", key, value));
            });


            added.forEach(key -> {
                var value = map2.get(key).toString();
                diffs.add(new Diff("added", key, value));
            });

            common.forEach(key -> {
                var value1 = map1.get(key).toString();
                var value2 = map2.get(key).toString();

                if (value1.equals(value2)) {
                    diffs.add(new Diff("same", key, value1));
                } else {
                    diffs.add(new Diff("before", key, value1));
                    diffs.add(new Diff("after", key, value2));
                }
            });
        }

        return sortResult(diffs, format);
    }
}
