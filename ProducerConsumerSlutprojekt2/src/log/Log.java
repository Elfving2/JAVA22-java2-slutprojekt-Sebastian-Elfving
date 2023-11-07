package log;
import view.GUI;

import javax.swing.text.BadLocationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class Log {
    public static final String txtFilePath = "src\\log\\Log.txt";
    static ArrayList<String> arrayList = new ArrayList<>();
    public static void write(String writeToFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txtFilePath,true))){
            bufferedWriter.write(writeToFile);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void checkFileContent(GUI gui) {
        try {
            ArrayList<String> currentContent = (ArrayList<String>) Files.readAllLines(Path.of(txtFilePath));
            if (!currentContent.equals(arrayList)) {
                System.out.println("File content has changed!");
                arrayList = new ArrayList<>(currentContent);
                gui.textArea.getDocument().insertString(0,arrayList.get(arrayList.size() - 1) + "\n", null);
                gui.textArea.setCaretPosition(0);
            }
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }
}
