package converter.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    FileUtils fileUtils = new FileUtils();
    File file_J = new File("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/test/java/converter/utils/test.json");
    File file_Y = new File("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/test/java/converter/utils/test.yaml");

    @Test
    void getFilesList() throws IOException {
        Set<File> files = fileUtils.getFilesList("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/main/java/test-file");
        files.contains(Files.getFileStore(Path.of("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/main/java/test-file/y_test.yaml")));
        files.contains(Files.getFileStore(Path.of("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/main/java/test-file/j_test.json")));
        assertEquals(2, files.size());
    }

    @Test
    public void isJson_true() {
      assertTrue(fileUtils.isJson(file_J));
    }
    @Test
    public void isJson_false() {
        assertFalse(fileUtils.isJson(file_Y));
    }

    @Test
    void isYaml_true() {
        assertTrue(fileUtils.isYaml(file_Y));
    }
    @Test
    void isYaml_false() {
        assertFalse(fileUtils.isYaml(file_J));
    }

    @Test
    void getOutputFolder() {
      assertEquals("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/test/java/converter/utils/created",
              fileUtils.getOutputFolder(file_J));
    }

    @Test
    void getOutputFile() {
        assertEquals("/Users/tetyanaburii/Desktop/Hillel/MyHomeProject/json-yaml-file-converter/src/test/java/converter/utils/created/test",
                fileUtils.getOutputFilePathWithoutExtension(file_J));

    }

    @Test
    void getFileExtension_true() throws IOException {
        assertEquals("json",fileUtils.getFileExtension(file_J));
        assertEquals("yaml",fileUtils.getFileExtension(file_Y));
    }

    @Test
    void getNameWithoutExtension() {
        assertEquals("test",fileUtils.getNameWithoutExtension(file_Y));
        assertEquals("test",fileUtils.getNameWithoutExtension(file_J));
    }
}