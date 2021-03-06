package converter.cprocesor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static converter.Converter.logger;
import static converter.utils.FileUtils.getOutputFilePathWithoutExtension;

public class ConvertToYaml implements Convert {

    @Override public void convert(File file) throws IOException, URISyntaxException {
        try {
            long m = System.currentTimeMillis();
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper jsonReader = new ObjectMapper(new JsonFactory());
            Object obj = jsonReader.readValue(content, Object.class);
            ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
            File newFile = new File(getOutputFilePathWithoutExtension(file) + ".yaml");
            yamlWriter.writeValue(newFile, obj);

            logger.info(file, newFile);
            logger.info((double) (System.currentTimeMillis() - m));

        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            logger.error(file.getName() + " was no converted.", ex);
        }

    }
}
