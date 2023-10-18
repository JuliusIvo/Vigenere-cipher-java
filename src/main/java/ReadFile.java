import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    public String retrieveResouceFileAsString(String filename){
        try {
            return new String(Files.readAllBytes(Paths.get(getClass().getResource(filename).toURI())));
        } catch (IOException e){
            System.out.println("Something failed");
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            System.out.println("Something failed");
            throw new RuntimeException(e);
        }
    }
}
