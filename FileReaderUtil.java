import java.io.*;
import java.util.*;
import javax.swing.*;

class FileReaderUtil {


    public static List<String[]> readDataset(String filePath) {
        List<String[]> dataset = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming each line has two texts separated by a comma
                String[] texts = line.split(",");
                if (texts.length == 2) {
                    dataset.add(texts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }


    public static String selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Dataset File");

        // Set to only accept text files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}
