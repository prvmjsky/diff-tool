package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class DifferTest {

    private static Path getFixturePath(String filename) {
        return Paths.get("src", "test", "resources", "fixtures", filename)
            .toAbsolutePath().normalize();
    }

    private static String readFixture(String filename) throws IOException {
        var path = getFixturePath(filename);
        return Files.readString(path);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file2.json",
        "file1.yml, file2.yaml",
        "file1.json, file2.yaml"
    })
    public void testDefault(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();

        var expected = readFixture("stylishDiff");
        var actual = Differ.generate(filepathString1, filepathString2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file2.json",
        "file1.yml, file2.yaml",
        "file1.json, file2.yaml"
    })
    public void testStylishDiffer(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();

        var expected = readFixture("stylishDiff");
        var actual = Differ.generate(filepathString1, filepathString2, "stylish");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file2.json",
        "file1.yml, file2.yaml",
        "file1.json, file2.yaml"
    })
    public void testPlainDiffer(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();

        var expected = readFixture("plainDiff");
        var actual = Differ.generate(filepathString1, filepathString2, "plain");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file2.json",
        "file1.yml, file2.yaml",
        "file1.json, file2.yaml"
    })
    public void testJsonDiffer(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();

        var expected = readFixture("jsonDiff.json");
        var actual = Differ.generate(filepathString1, filepathString2, "json");
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, empty.yml",
        "empty.json, empty.json"
    })
    public void testEmptyInputFiles(String filepath1, String filepath2) {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();
        assertThrows(IOException.class, () -> Differ.generate(filepathString1, filepathString2, "stylish"));
    }
}
