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
    File resultLog = new File(new Converter().getPath() + File.separator + "result.log");

    public Logger() throws URISyntaxException {
    }

    public void info(File file, File newFile) throws IOException, URISyntaxException {

        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println("---" + date + "---");
        writer.println("Name: " + file.getName() + "-->>" + newFile.getName());
        writer.println("Size: " + Files.size(file.toPath()) + "-->>" + Files.size(newFile.toPath()));
        writer.close();
    }

    public void info(String info) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println(info);
        writer.close();
    }

    public void info(Double info) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println(info);
        writer.close();
    }

    public void error(String info, Throwable e) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println("---" + date + "---");
        writer.println(info + e.toString());
        writer.close();
    }

}
