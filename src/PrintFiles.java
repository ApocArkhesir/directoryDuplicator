import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class PrintFiles
    extends SimpleFileVisitor<Path> {

    public FileVisitResult visitFile(Path file, Path oldFile) throws IOException {
        if (!(oldFile.getFileName() == file.getFileName())) {
            try {
                Files.copy(oldFile, file, LinkOption.NOFOLLOW_LINKS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }
}
