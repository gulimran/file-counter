package imran;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileCounterRecursiveTest {

    private FileCounterRecursive classToTest;

    @Before
    public void setup() {
        classToTest = new FileCounterRecursive();
    }

    @Test
    public void shouldReturnOneCount_ForDirectoryContainingOneFile() throws Exception {
        // given
        String inputDirectoryName = "directory-with-one-file";

        // when
        Integer count = classToTest.getFileCount(inputDirectoryName);

        // then
        assertThat(count, is(1));
    }

    @Test
    public void shouldReturnTwoCount_ForDirectoryContainingTwoFiles() throws Exception {
        // given
        String inputDirectoryName = "directory-with-multiple-files";

        // when
        Integer count = classToTest.getFileCount(inputDirectoryName);

        // then
        assertThat(count, is(2));
    }

    @Test
    public void shouldReturnFourCount_ForDirectoryContainingSubDirectoryAndTwoFiles() throws Exception {
        // given
        String inputDirectoryName = "directory-with-subdirectory";

        // when
        Integer count = classToTest.getFileCount(inputDirectoryName);

        // then
        assertThat(count, is(4));
    }

    @Test
    public void shouldReturnZeroCount_ForEmptyDirectory() throws Exception {
        // given
        String inputDirectoryName = "empty-directory";

        // when
        Integer count = classToTest.getFileCount(inputDirectoryName);

        // then
        assertThat(count, is(0));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldExpectException_ForNonExistingDirectory() throws Exception {
        // given
        String inputDirectoryName = "non-existing-directory";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Given input directory does not exist");

        // when
        classToTest.getFileCount(inputDirectoryName);
    }
}
