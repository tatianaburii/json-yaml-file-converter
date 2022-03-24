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


public class ConvertToJson implements Convert {
    @Override
    public void convert(File file) throws IOException, URISyntaxException {
        try {
            long m = System.currentTimeMillis();
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(content, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper(new JsonFactory());
            File newFile = new File(getOutputFilePathWithoutExtension(file) + ".json");
            jsonWriter.writeValue(newFile, obj);

            logger.info(file, newFile);
            logger.info(((double) (System.currentTimeMillis() - m)));

        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            logger.error(file.getName() + " was not converted.", ex);
        }

    }
}
