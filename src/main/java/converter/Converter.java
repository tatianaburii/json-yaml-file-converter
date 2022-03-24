package converter;

import converter.cprocesor.ConvertToJson;
import converter.cprocesor.ConvertToYaml;
import converter.logger.MyLogger;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import static converter.utils.FileUtils.*;

@Data
public class Converter {
    public static MyLogger logger;
    static {
        try {
            logger = new MyLogger();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        Converter converter = new Converter();

        String path;
        if (args.length != 0) {
            path = args[0];
        }else {
            path = getPathToMainFolder();
        }
        converter.checkFileAndCallConverter(getFilesList(path));


    }

    public void checkFileAndCallConverter(Set<File> files) throws IOException, URISyntaxException {
        for (File file : files) {
            if (isYaml(file)) {
                new ConvertToJson().convert(file);
            } else if (isJson(file)) {
                new ConvertToYaml().convert(file);
            } else {
                System.out.println("File called [" + file.toPath().getFileName() + "] is not valid!");
            }
        }
    }

}
