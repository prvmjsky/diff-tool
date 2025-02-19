package hexlet.code;

import org.junit.jupiter.api.Test;

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

    @Test
    public void testDefault() throws IOException {
        var file1 = getFixturePath("file1.json").toString();
        var file2 = getFixturePath("file2.json").toString();
        var diff = readFixture("diff");
        assertEquals(diff, Differ.generate(file1, file2));
    }
}
