package thread;

import java.io.*;
import java.util.function.Predicate;


public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        return readFile(data -> true);
    }
    public String getContentWithoutUnicode() throws IOException {
        return readFile(data -> data < 0x80);
    }

    private String readFile(Predicate<Integer> predicate) throws IOException {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader fis = new BufferedReader(new FileReader(file))) {
            int rsl;
            while ((rsl = fis.read()) > 0) {
                if (predicate.test(rsl)) {
                    sb.append(rsl);
                }
            }
        }
        return sb.toString();
    }
}