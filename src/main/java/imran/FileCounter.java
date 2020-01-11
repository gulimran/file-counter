package imran;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCounter {

    public Integer getFileCount(String inputDirectoryName)
            throws IOException, URISyntaxException {

        List<String> fileNames;
        URL resource = getClass().getClassLoader().getResource(inputDirectoryName);

        if (resource == null) {
            throw new IllegalArgumentException("Given input directory does not exist");
        }

        try (Stream<Path> dirStream = Files.walk(
                Paths.get(resource.toURI()), FileVisitOption.FOLLOW_LINKS)) {

            fileNames = dirStream.filter(path -> !path.toFile().isDirectory())
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }

        return fileNames.size();
    }
}
