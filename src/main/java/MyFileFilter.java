import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * @author galya_semenova
 * Custom file filter required to display only doc, docx, txt format in the file selection window
 */
class MyFileFilter extends FileFilter {
    /**
     * Tests whether the specified abstract pathname should be included in a pathname list.
     * @param file The abstract pathname to be tested
     * @return true if and only if pathname should be included
     */
    @Override
    public boolean accept(File file)
    {
        String name = file.getName();
        return file.isDirectory () || name.toLowerCase().endsWith(".docx") || name.toLowerCase().endsWith(".doc") || name.toLowerCase().endsWith(".txt"); // Отображать только каталог и файлы docx,doc,txt
    }

    /**
     * The description of this filter. txt, doc, docx format.
     * @return necessary format
     */
    // Функция описания типов файлов
    @Override
    public String getDescription() {
        return "*.docx;*.txt";
    }
}
