package converter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Set;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import static com.google.common.io.MoreFiles.getFileExtension;
import static com.google.common.io.MoreFiles.getNameWithoutExtension;
@Slf4j
public class Converter {
    private static final Logger logger = LoggerFactory.getLogger(Converter.class);


    public void converterRun() throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path2 = new File((Runner.class
                .getProtectionDomain()
                .getCodeSource().getLocation()
                .toURI()).getPath())
                .getParent();
        System.out.println("Input path: ");

        String path = reader.readLine();

        if (!(path.equals(""))) {
            checkFileAndCallConverter(listFilesUsingJavaIO(path));
        } else {
            checkFileAndCallConverter(listFilesUsingJavaIO(path2));

        }

    }

    private void convertYamlToJson(File file) {
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj;
            obj = yamlReader.readValue(content, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper(new JsonFactory());
            File newFile = new File(createFileName(file) + ".json");
            jsonWriter.writeValue(newFile, obj);
            logger.info(file.getName() + "-->>" + newFile.getName());


        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("File was not created!");
        }
    }

    private void convertJsonToYaml(File file) {
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper jsonReader = new ObjectMapper(new JsonFactory());
            Object obj = jsonReader.readValue(content, Object.class);
            ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
            File newFile = new File(createFileName(file) + ".yaml");
            yamlWriter.writeValue(newFile, obj);
            logger.info(file.getName() + "-->>" + newFile.getName() );


        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("File was not created!");
        }
    }

    public Set<File> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }

    public void checkFileAndCallConverter(Set<File> files) throws IOException {
        for (File file : files) {
            if (getFileExtension(file.toPath()).equals("json")) {
                convertJsonToYaml(file);
            } else if (getFileExtension(file.toPath()).equals("yaml")) {
                convertYamlToJson(file);
            } else {
                System.out.println("File called " + file.getName() + " is not valid!");
            }
        }
    }

    public String createFolder(File file) {
        File folder = new File(file.getParent() + File.separator + "created");
        folder.mkdir();

        return folder.getPath();
    }

    public String createFileName(File file) {
        return createFolder(file) + File.separator + getNameWithoutExtension(file.toPath());
    }

}
