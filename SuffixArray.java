import java.util.Arrays;

public class SuffixArray {
    private String text;
    private Integer[] index;

    public SuffixArray(String s) {
        text = s;
        int n = s.length();
        index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        Arrays.sort(index, (i, j) -> {
            for (int k = 0; i + k < n && j + k < n; k++) {
                if (text.charAt(i + k) != text.charAt(j + k)) {
                    return text.charAt(i + k) - text.charAt(j + k);
                }
            }
            return 0;
        });
    }

    public int index(int i) {
        return index[i];
    }
}
