import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * @author galya_semenova
 * Class method testing MyFrame class
 */
public class MyFrameTest {
    /**
     * Test reading data from a file. The correctness of the information received from it is checked
     * @throws IOException - exception when data cannot be read from a file
     */
    @Test
    public void readData() throws IOException {
        String result = MyFrame.readDataFile("src/test/resources/TXT Files/testReadDataFile.txt");
        assertEquals("Моя страна Россия, проверка файла", result);
    }

    /**
     * Test for checking reading data from an empty file
     * @throws IOException - exception when data cannot be read from a file
     */
    @Test
    public void readEmptyFile() throws IOException {
        String result = MyFrame.readDataFile("src/test/resources/TXT Files/testReadEmptyFile.txt");
        assertEquals("", result);
    }
    /**
     * Test for checking replacing words in file
     * @throws IOException - exception when data cannot be read from a file
     */
    @Test
    public void replaceWord() throws IOException {
        String result = MyFrame.replaceWord("src/test/resources/TXT Files/testReadDataFile.txt");
        assertEquals("Моя страна РФ, проверка файла", result);
    }
    /**
     * Test for checking replacing words in an empty file
     * @throws IOException - exception when data cannot be read from a file
     */
    @Test
    public void replaceWord_empty() throws IOException {
        String result = MyFrame.replaceWord("src/test/resources/TXT Files/testReadEmptyFile.txt");
        assertEquals("", result);
    }
}