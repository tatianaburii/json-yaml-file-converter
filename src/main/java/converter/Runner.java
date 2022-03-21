package converter;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.URISyntaxException;
import java.lang.String;

@Slf4j
public class Runner {

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Converter().converterRun();
    }

}
