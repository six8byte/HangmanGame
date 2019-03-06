import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Lexicon {

    private String word;
    private String path = "res/words.txt";
    private List<String> lines;

    public Lexicon() throws IOException {
        readLines(path);
    }

    private void readLines(String filename) throws IOException {
        Scanner file = new Scanner(new File(filename));
        lines = new ArrayList<>();

        while (file.hasNextLine()) {
            lines.add(file.nextLine());
        }
        file.close();
    }

    public String getWord(){
        Random r = new Random();
        int line = r.nextInt(10);
        return lines.get(line);
    }

}
