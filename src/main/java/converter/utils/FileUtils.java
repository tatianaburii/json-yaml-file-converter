package converter.utils;

import converter.Converter;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileUtils {
    public static Set<File> getFilesList(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }

    public static boolean isJson(File file) {
        return getFileExtension(file).equals("json");
    }

    public static boolean isYaml(File file) {
        return getFileExtension(file).equals("yaml");
    }

    public static String getOutputFolder(File file) {
        File newFile = new File(file.getParent() + File.separator + "created");
        newFile.mkdir();
        return newFile.getAbsolutePath();
    }

    public static String getOutputFilePathWithoutExtension(File file) {
        return getOutputFolder(file) + File.separator + getNameWithoutExtension(file);
    }

    public static String getFileExtension(File file) {
        StringBuilder fileNameReverse = new StringBuilder(file.getName()).reverse();
        return new StringBuilder(fileNameReverse.substring(0, fileNameReverse.indexOf("."))).reverse().toString();
    }

    public static String getNameWithoutExtension(File file) {
        return file.getName().substring(0, file.getName().indexOf(getFileExtension(file)) - 1);
    }
    public static String getPathToMainFolder() throws URISyntaxException {
   return new File((Converter.class.getProtectionDomain()
            .getCodeSource().getLocation().toURI()).getPath()).getParent();}


}
