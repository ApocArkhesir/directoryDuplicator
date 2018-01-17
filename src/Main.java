import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length < 1) {} //
        if (args.length < 2) {} //

        Path startingDirectory = Paths.get(args[0]);
        Path targetDirectory   = Paths.get(args[1]);

        FileSynchronizer fileSynchronizer = new FileSynchronizer(startingDirectory, targetDirectory);

        Thread synchronizeThread = new Thread(() -> {
            try {
                Files.walkFileTree(startingDirectory, fileSynchronizer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        synchronizeThread.start();
    }
}
