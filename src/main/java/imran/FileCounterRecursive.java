package imran;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileCounterRecursive {

    public Integer getFileCount(String inputDirectoryName) throws URISyntaxException {
        if (inputDirectoryName == null || inputDirectoryName.isEmpty()) {
            throw new IllegalArgumentException("Given input directory cannot be null or empty");
        }

        URL resource = getClass().getClassLoader().getResource(inputDirectoryName);

        if (resource == null) {
            throw new IllegalArgumentException("Given input directory does not exist");
        }

        File file = new File(resource.toURI());

        return getFileCountRecursive(file);
    }

    private Integer getFileCountRecursive(File file) {
        File[] files = file.listFiles();
        Integer count = 0;

        for (File aFile : files) {
            if (aFile.isDirectory()) {
                count += getFileCountRecursive(aFile);
            }
            else {
                count++;
            }
        }

        return count;
    }
}
