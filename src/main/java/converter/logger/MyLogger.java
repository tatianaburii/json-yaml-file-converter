package converter.logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;

import static converter.utils.FileUtils.getPathToMainFolder;

public class MyLogger {

    public MyLogger() throws URISyntaxException {
    }

    File resultLog = new File(getPathToMainFolder() + File.separator + "result.log");

    public void info(File file, File newFile) throws IOException, URISyntaxException {

        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println("---" + LocalDate.now() + " "+ LocalTime.now() + "---");
        writer.println("Name: " + file.getName() + "-->>" + newFile.getName());
        writer.println("Size: " + Files.size(file.toPath()) + "-->>" + Files.size(newFile.toPath()));
        writer.close();
    }

    public void info(String info) throws IOException, URISyntaxException {

        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println("---" + LocalDate.now() + " "+ LocalTime.now() + "---");
        writer.println(info);
        writer.close();
    }

    public void info(Double info) throws IOException, URISyntaxException {

        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println("---" + LocalDate.now() + " "+ LocalTime.now() + "---");
        writer.println(info);
        writer.close();
    }

    public void error(String info, Throwable e) throws IOException, URISyntaxException {
        PrintWriter writer = new PrintWriter((new FileWriter(resultLog, true)));
        writer.println("---" + LocalDate.now() + " "+LocalTime.now() + "---");
        writer.println(info + e.toString());
        writer.close();
    }
}
