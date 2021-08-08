package thread;

import java.io.*;
import java.util.function.IntPredicate;

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

    private String readFile(IntPredicate predicate) throws IOException {
        StringBuilder sb = new StringBuilder();
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          for (String line = br.readLine(); line != null; line = br.readLine()) {
              sb.append(line);
          }
      }
      return sb.toString();
    }
}