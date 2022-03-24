package converter;

import converter.cprocesor.ConvertToJson;
import converter.cprocesor.ConvertToYaml;
import converter.utils.FileUtils;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
@Data
public class Converter {

    private final String path = new File((Converter.class.getProtectionDomain()
            .getCodeSource().getLocation().toURI()).getPath()).getParent();

    public static void main(String[] args) throws URISyntaxException, IOException {

        Converter converter = new Converter();

        if (args.length != 0) {
            converter.checkFileAndCallConverter(new FileUtils().getFilesList(args[0]));
        } else {
            converter.checkFileAndCallConverter(new FileUtils().getFilesList(converter.path));}

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
    public Converter() throws URISyntaxException {
    }
}
