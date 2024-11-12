import java.util.*;
import javax.swing.*;

public class Main {
    private static String lcp(String s, int p, String t, int q) {
        int n = Math.min(s.length() - p, t.length() - q);
        for (int i = 0; i < n; i++) {
            if (s.charAt(p + i) != t.charAt(q + i))
                return s.substring(p, p + i);
        }
        return s.substring(p, p + n);
    }

    private static int compare(String s, int p, String t, int q) {
        int n = Math.min(s.length() - p, t.length() - q);
        for (int i = 0; i < n; i++) {
            if (s.charAt(p + i) != t.charAt(q + i))
                return s.charAt(p + i) - t.charAt(q + i);
        }
        if (s.length() - p < t.length() - q) return -1;
        else if (s.length() - p > t.length() - q) return +1;
        else return 0;
    }

    public static String lcs(String s, String t) {
        SuffixArray suffix1 = new SuffixArray(s);
        SuffixArray suffix2 = new SuffixArray(t);

        String lcs = "";
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            int p = suffix1.index(i);
            int q = suffix2.index(j);
            String x = lcp(s, p, t, q);
            if (x.length() > lcs.length()) lcs = x;
            if (compare(s, p, t, q) < 0) i++;
            else j++;
        }
        return lcs;
    }

    public static void checkPlagiarism(List<String[]> dataset) {
        for (String[] texts : dataset) {
            String s = texts[0].trim();
            String t = texts[1].trim();

            String commonSubstring = lcs(s, t);
            int lcsLength = commonSubstring.length();

            System.out.println("Text 1: " + s);
            System.out.println("Text 2: " + t);
            System.out.println("Longest Common Substring: '" + commonSubstring + "'");

            if (lcsLength > 20) {  // Flag as plagiarism if LCS is longer than 20 characters
                System.out.println("Plagiarism Detected!");
            } else {
                System.out.println("No plagiarism detected.");
            }
            System.out.println("-------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        // Select the file using JFileChooser
        String filePath = FileReaderUtil.selectFile();

        if (filePath != null) {
            // Read the dataset from the selected file
            List<String[]> dataset = FileReaderUtil.readDataset(filePath);
            // Check for plagiarism in the dataset
            checkPlagiarism(dataset);
        }
    }
}
