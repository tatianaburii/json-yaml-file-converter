package converter.cprocesor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public interface Convert {
    void convert(File file) throws IOException, URISyntaxException;
}
