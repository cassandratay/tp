package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility methods for reading, writing, and creating files.
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    /**
     * Returns whether the given path exists and refers to a regular file.
     */
    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     *
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file The file to create if missing.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file The file to create.
     * @throws IOException If the file or its parent directories cannot be created.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates the parent directories of the given file path, if any.
     *
     * @param file The file whose parent directories should be created.
     * @throws IOException If the parent directories cannot be created.
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Reads the full contents of a file as a UTF-8 string.
     *
     * @param file The file to read.
     * @return The file contents.
     * @throws IOException If the file cannot be read.
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes the given string to a file using UTF-8 encoding.
     *
     * @param file The file to write to.
     * @param content The content to write.
     * @throws IOException If the file cannot be written.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

}
