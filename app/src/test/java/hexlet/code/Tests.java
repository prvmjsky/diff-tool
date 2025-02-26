package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

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
        "file1.yml, file2.yml"
    })
    public void testStylishDiffer(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();
        var format = "stylish";

        var expected = readFixture("stylishDiff");
        var actual = Differ.generate(filepathString1, filepathString2, format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file2.json",
        "file1.yml, file2.yml"
    })
    public void testPlainDiffer(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();
        var format = "plain";

        var expected = readFixture("plainDiff");
        var actual = Differ.generate(filepathString1, filepathString2, format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.json, file2.json",
        "file1.yml, file2.yml"
    })
    public void testJsonDiffer(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();
        var format = "json";

        var expected = Parser.toString(readFixture("jsonDiff.json"));
        var actual = Differ.generate(filepathString1, filepathString2, format);
        assertEquals(expected, actual);
    }

    @Test
    public void howDoesParserToStringLook() throws IOException {
        System.out.println(Parser.toString(readFixture("file2.json")));
    }
}
