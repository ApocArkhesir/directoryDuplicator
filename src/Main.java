import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Path startingDir = Paths.get("/home/apoc/Documents/testdir");
        PrintFiles pf = new PrintFiles();
        Files.walkFileTree(startingDir, pf);
    }
}
