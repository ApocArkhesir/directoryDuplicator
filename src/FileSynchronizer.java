import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileSynchronizer
    extends SimpleFileVisitor<Path> {

    private final Path fromDirectory;
    private final Path toDirectory;

    public FileSynchronizer(
        Path fromDirectory,
        Path toDirectory
    ) {

        this.fromDirectory = fromDirectory;
        this.toDirectory = toDirectory;
    }

    @Override
    public FileVisitResult visitFile(
        Path                sourceFilePath,
        BasicFileAttributes attributes
    ) throws IOException {

        Path targetFilePath = getTargetPath(sourceFilePath);

        try {
            Files.copy(sourceFilePath, targetFilePath, LinkOption.NOFOLLOW_LINKS);
            System.out.println(MessageFormat.format("Copied file from \"{0}\" to \"{1}\"...", sourceFilePath, targetFilePath));
        }
        catch (FileAlreadyExistsException e) {
            System.out.println(MessageFormat.format("File \"{0}\" already exists at \"{1}\"...", sourceFilePath, targetFilePath));
        }

        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(
        Path                directoryPath,
        BasicFileAttributes attributes
    ) throws IOException {

        Path targetDirectoryPath = getTargetPath(directoryPath);

        try {
            Files.createDirectory(targetDirectoryPath);
            System.out.println(MessageFormat.format("Copying directory \"{0}\" to \"{1}\"...", directoryPath, targetDirectoryPath));
        }
        catch (FileAlreadyExistsException e) {
            System.out.println(MessageFormat.format("Directory \"{0}\" already exists at \"{1}\"...", directoryPath, targetDirectoryPath));
        }

        return FileVisitResult.CONTINUE;
    }

    private Path getTargetPath(Path path) {
        Path relativePath = fromDirectory.relativize(path);

        return toDirectory.resolve(relativePath);
    }
}
