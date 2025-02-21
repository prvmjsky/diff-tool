package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

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
    public void testDefaultJson(String filepath1, String filepath2) throws IOException {
        var filepathString1 = getFixturePath(filepath1).toString();
        var filepathString2 = getFixturePath(filepath2).toString();
        var diff = readFixture("diff");
        assertEquals(diff, Differ.generate(filepathString1, filepathString2));
    }
}
