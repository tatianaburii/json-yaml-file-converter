package converter.utils;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileUtils {
    public Set<File> getFilesList(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }

    public boolean isJson(File file) {
        return getFileExtension(file).equals("json");
    }

    public boolean isYaml(File file) {
        return getFileExtension(file).equals("yaml");
    }

    public String getOutputFolder(File file) {
        File newFile = new File( file.getParent() + File.separator + "created");
        newFile.mkdir();
        return newFile.getAbsolutePath();
    }

    public String getOutputFile(File file) {
        return getOutputFolder(file) + File.separator + getNameWithoutExtension(file);
    }

    public String getFileExtension(File file) {
        StringBuilder fileNameReverse = new StringBuilder(file.getName()).reverse();
        return new StringBuilder(fileNameReverse.substring(0, fileNameReverse.indexOf("."))).reverse().toString();
    }

    public String getNameWithoutExtension(File file) {
        return file.getName().substring(0, file.getName().indexOf(getFileExtension(file)));
    }


}
