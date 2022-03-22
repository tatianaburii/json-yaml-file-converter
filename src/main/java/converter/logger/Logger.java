package converter.logger;

import converter.Converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Date;

public class Logger {
    private Date date = new Date();
    public File createLogFile() throws URISyntaxException {
        String folder = new File((Converter.class
                .getProtectionDomain()
                .getCodeSource().getLocation()
                .toURI()).getPath())
                .getParent();
        File logFile = new File(folder + File.separator + "result.log");
        return logFile;
    }
    public void writeResult(File file, File newFile) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(createLogFile(), true)));
        long bytesF = Files.size(file.toPath());
        long bytesNF = Files.size(newFile.toPath());
        writer.println("---" + date + "---");
        writer.println("Name: " + file.getName() + "-->>" +newFile.getName());
        writer.println("Size: " + bytesF + "-->>" + bytesNF);
        writer.close();
    }
    public void writeResult(String info) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(createLogFile(), true)));
        writer.println(info);
        writer.close();
    }
    public void writeResult(Double info) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(createLogFile(), true)));
        writer.println(info);
        writer.close();
    }

}
