package converter;

import converter.cprocesor.ConvertToJson;
import converter.cprocesor.ConvertToYaml;
import converter.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public class Converter {

    public static void main(String[] args) throws URISyntaxException, IOException {

        Converter converter = new Converter();
        String path2 = new File((Converter.class
                .getProtectionDomain()
                .getCodeSource().getLocation()
                .toURI()).getPath())
                .getParent();


        if (args.length != 0) {
            converter.checkFileAndCallConverter(new FileUtils().getFilesList(args[0]));
        } else {
            converter.checkFileAndCallConverter(new FileUtils().getFilesList(path2));}

    }

    public void checkFileAndCallConverter(Set<File> files) throws IOException, URISyntaxException {
        for (File file : files) {
            if (new FileUtils().isYaml(file)) {
                new ConvertToJson().convert(file);
            } else if (new FileUtils().isJson(file)) {
                new ConvertToYaml().convert(file);
            } else {
                System.out.println("File called [" + file.toPath().getFileName() + "] is not valid!");
            }
        }
    }
}
