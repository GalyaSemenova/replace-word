import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author galya_semenova
 * Creating a graphical interface that allows you to select a file, replace it
 * and display the final result on the screen.
 */
public class MyFrame extends JFrame implements ActionListener {
    /**
     * charset - specifying the encoding used in the files
     */
    static Charset charset = StandardCharsets.UTF_8;

    /**
     * btnSelectFile - button of select file
     */
    private final JButton btnSelectFile;
    /**
     * btnReplaceWords - button of replace word 'Россия' to 'РФ' in chosen file
     */
    private final JButton btnReplaceWords;
    /**
     * label - the field where the contents of the text file will be displayed
     */
    private final JLabel label;
    /**
     * fName - name of the file
     */
    private String fName;
    /**
     * s - string for storing file contents
     */
    private String s;

    /**
     * Constructor for creating frame with necessary functional
     */
    MyFrame() {
        this.setTitle("Replace Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        btnSelectFile = new JButton("Select File");
        btnSelectFile.addActionListener(this);
        btnSelectFile.setFocusable(false);

        btnReplaceWords = new JButton("Replace words");
        btnReplaceWords.addActionListener(this);
        btnReplaceWords.setFocusable(false);

        label = new JLabel("",JLabel.CENTER);
        label.setPreferredSize(new Dimension(550, 400));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        this.add(label);
        this.add(btnSelectFile);
        this.add(btnReplaceWords);

        this.pack();
        this.setResizable(false);
        this.setSize(600, 500);
        this.setVisible(true);
    }

    /**
     * Method sent to all action listeners that are registered on the relevant component
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSelectFile)
            selectFile();

        if (e.getSource() == btnReplaceWords)
            replaceWords();
    }

    /**
     * The method of replacing words in the file. the replace Word static method is called
     * with the passed path to the file, the returned result is placed in label. if an exception is thrown,
     * the error text is displayed in the label.
     * @throws IOException - exception when data cannot be read from a file
     * @throws NullPointerException - exception when the file was not selected by the user
     */
    private void replaceWords() {
        try {
            s = replaceWord(fName);
            label.setText("<html>" + s + "</html>");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (NullPointerException ex) {
            label.setText("<html><h1>Error! File is not selected.</h1></html>");
        }
    }

    /**
     * The method of selecting a file using jfilechooser using the myFilter filter.
     * If the operation was successful, the file path is written to the string variable fName
     */
    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser("./src/main/resources/TXT Files");
        MyFileFilter filter = new MyFileFilter();
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);

        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            fName = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                s = readDataFile(fName);
                label.setText("<html>" + s + "</html>");
            } catch (IOException ex) {
                label.setText("<html><h1>Error! Format of file is not correct.</h1></html>");
            }
        }
        if (response == JFileChooser.CANCEL_OPTION) {
            label.setText("<html><h1>File is not selected.</h1></html>");
        }
    }

    /**
     * This method receives the input path to the file as a string, creates an object of the path class
     * and uses the static Files.ReadString method to read information from there.
     * @param fName - name of upload file
     * @return returns the data read from the file as a string
     * @throws IOException - if an incorrect file path is passed and data cannot be read from it, this exception is thrown
     */
    public static String readDataFile(String fName) throws IOException {
        Path path = Paths.get(fName);
        return Files.readString(path, charset);
    }

    /**
     * This method receives the input path to the file as a string, reads data from there into str
     * and replaces each substring of this string, which corresponds to the literal target sequence
     * specified by the literal replacement sequence. Then saves the modified text to a file
     * @param fName - name of upload file
     * @return a string containing the modified data of a text file
     * @throws IOException - if an incorrect file path is passed and data cannot be read from it, this exception is thrown
     */
    public static String replaceWord(String fName) throws IOException {
        final String search = "Россия";
        final String replace = "РФ";
        String str = readDataFile(fName);
        Path path = Paths.get(fName);
        str = str.replace(search, replace);
        Files.writeString(path, str, charset);
        return str;
    }
}