package gamejam.ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadWrite {

    public static String readFile(String pathFile) throws IOException {

        Path path = Paths.get(pathFile);
        //byte[] bytes = Files.readAllBytes(path);
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: Files.readAllLines(path, StandardCharsets.UTF_8)) {
            if (!line.isEmpty()) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    // saves the content into a file to the pathFile
    public static void saveFile(String content, String pathFile) throws IOException {
        final Path path = Paths.get(pathFile);

        try (
                final BufferedWriter writer = Files.newBufferedWriter(path,
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        ) {
            writer.write(content);
            writer.flush();
        }
    }
}
